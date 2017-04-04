/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ChorbiService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChorbiTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de banear un chorbi:
	//test positivo
	@Test
	public void banChorbiTest1() {
		this.templateBanChorbi("admin", 1098, null);
	}
	//sin loguearse
	@Test
	public void banChorbiTest2() {
		this.templateBanChorbi(null, 1098, IllegalArgumentException.class);
	}
	//no logeado como admin
	@Test
	public void banChorbiTest3() {
		this.templateBanChorbi("chorbi1", 1098, IllegalArgumentException.class);
	}
	//chorbi no existente
	@Test
	public void banChorbiTest4() {
		this.templateBanChorbi("admin", 288, NullPointerException.class);
	}

	//Caso de uso de desbanear un chorbi:
	//test positivo
	@Test
	public void unbanChorbiTest1() {
		this.templateUnbanChorbi("admin", 1098, null);
	}
	//sin loguearse
	@Test
	public void unbanChorbiTest2() {
		this.templateUnbanChorbi(null, 1098, IllegalArgumentException.class);
	}
	//no logeado como admin
	@Test
	public void unbanChorbiTest3() {
		this.templateUnbanChorbi("chorbi1", 1098, IllegalArgumentException.class);
	}
	//chorbi no existente
	@Test
	public void unbanChorbiTest4() {
		this.templateUnbanChorbi("admin", 288, NullPointerException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateBanChorbi(final String username, final int chorbiId, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			this.chorbiService.banChorbi(chorbiId);

			this.chorbiService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateUnbanChorbi(final String username, final int chorbiId, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			this.chorbiService.unbanChorbi(chorbiId);

			this.chorbiService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
