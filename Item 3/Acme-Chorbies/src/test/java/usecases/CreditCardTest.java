/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CreditCardService;
import utilities.AbstractTest;
import domain.CreditCard;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CreditCardTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CreditCardService	creditCardService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear una tarjeta de credito:
	//test positivo
	@Test
	public void createCreditCardTest1() {
		this.templateCreateCreditCard("chorbi4", "VISA", 200, 10, 2020, "chorbi1", "0", null);
	}
	//sin loguearse
	@Test
	public void createCreditCardTest2() {
		this.templateCreateCreditCard(null, "VISA", 200, 10, 2020, "chorbi1", "0", IllegalArgumentException.class);
	}
	//brand null
	@Test
	public void createCreditCardTest3() {
		this.templateCreateCreditCard("chorbi4", "", 200, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//cvv menor que 100
	@Test
	public void createCreditCardTest4() {
		this.templateCreateCreditCard("chorbi4", "VISA", 99, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//cvv mayor que 999
	@Test
	public void createCreditCardTest5() {
		this.templateCreateCreditCard("chorbi4", "VISA", 1000, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//mes menor que 1
	@Test
	public void createCreditCardTest6() {
		this.templateCreateCreditCard("chorbi4", "VISA", 200, 0, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//mes mayor que 12
	@Test
	public void createCreditCardTest7() {
		this.templateCreateCreditCard("chorbi4", "VISA", 200, 13, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//año pasado
	@Test
	public void createCreditCardTest8() {
		this.templateCreateCreditCard("chorbi4", "VISA", 200, 13, 2000, "chorbi1", "0", IllegalArgumentException.class);
	}
	//numero no coincidente al pattern
	@Test
	public void createCreditCardTest9() {
		this.templateCreateCreditCard("chorbi4", "VISA", 200, 13, 2000, "chorbi1", "1234", IllegalArgumentException.class);
	}

	//Caso de uso de editar una tarjeta de credito:
	//test positivo
	@Test
	public void editCreditCardTest1() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 200, 10, 2020, "chorbi1", "0", null);
	}
	//sin loguearse
	@Test
	public void editCreditCardTest2() {
		this.templateEditCreditCard(null, 1118, "VISA", 200, 10, 2020, "chorbi1", "0", IllegalArgumentException.class);
	}
	//brand null
	@Test
	public void editCreditCardTest3() {
		this.templateEditCreditCard("chorbi1", 1118, "", 200, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//cvv menor que 100
	@Test
	public void editCreditCardTest4() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 99, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//cvv mayor que 999
	@Test
	public void editCreditCardTest5() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 1000, 10, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//mes menor que 1
	@Test
	public void editCreditCardTest6() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 200, 0, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//mes mayor que 12
	@Test
	public void editCreditCardTest7() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 200, 13, 2020, "chorbi1", "0", ConstraintViolationException.class);
	}
	//año pasado
	@Test
	public void editCreditCardTest8() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 200, 13, 2000, "chorbi1", "0", IllegalArgumentException.class);
	}
	//numero no coincidente al pattern
	@Test
	public void editCreditCardTest9() {
		this.templateEditCreditCard("chorbi1", 1118, "VISA", 200, 13, 2000, "chorbi1", "1234", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateCreditCard(final String username, final String brandName, final int cvvCode, final int expirationMonth, final int expirationYear, final String holderName, final String number, final Class<?> expected) {
		Class<?> caught;
		CreditCard creditCard;
		caught = null;
		try {
			this.authenticate(username);

			creditCard = this.creditCardService.create();
			creditCard.setBrandName(brandName);
			creditCard.setCvvCode(cvvCode);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setHolderName(holderName);
			creditCard.setNumber(number);

			this.creditCardService.save(creditCard);
			this.creditCardService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditCreditCard(final String username, final int creditCardId, final String brandName, final int cvvCode, final int expirationMonth, final int expirationYear, final String holderName, final String number, final Class<?> expected) {
		Class<?> caught;
		final CreditCard creditCard;

		caught = null;
		try {
			this.authenticate(username);

			creditCard = this.creditCardService.getCreditCardByChorbi();
			creditCard.setBrandName(brandName);
			creditCard.setCvvCode(cvvCode);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setHolderName(holderName);
			creditCard.setNumber(number);

			this.creditCardService.save(creditCard);
			this.creditCardService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
