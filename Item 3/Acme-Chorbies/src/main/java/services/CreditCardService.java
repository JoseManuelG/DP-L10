
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.Chorbi;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed Repository -------------------------------------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting Services ------------------------------------------------------------

	@Autowired
	private SearchTemplateService	searchTemplateService;

	@Autowired
	private ChorbiService			chorbiService;


	// Constructor --------------------------------------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------------------------

	public CreditCard create() {
		CreditCard result;
		result = new CreditCard();
		return result;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;
		result = this.creditCardRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public CreditCard findOne(final int creditCardId) {
		CreditCard result;
		result = this.creditCardRepository.findOne(creditCardId);
		return result;
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard, "La tarjeta de crédito no puede ser nula");
		CreditCard result;
		result = this.creditCardRepository.save(creditCard);
		return result;
	}

	public void delete(final CreditCard creditCard) {
		Assert.notNull(creditCard, "La tarjeta de crédito no puede ser nula");
		final Chorbi chorbi = this.chorbiService.findChorbiByPrincipal();
		//Tengo q ver como meter las restricciones sin navegabilidad
		//Assert.isTrue(chorbi.getCreditCard().getId() == creditCard.getId(), "La tarjeta de crédito debe pertenecer al chorbi");
		//	chorbi.setCreditCard(null);
		//chorbiService.save(chorbi);
		this.creditCardRepository.delete(creditCard);
	}

	// Other Bussiness Methods --------------------------------------------------------

	public void checkCreditCard(final CreditCard creditCard) {
		long today, cardDate, sevenDays;
		Calendar calendar;

		Assert.notNull(creditCard, "creditCard.null.error");
		sevenDays = 7 * 24 * 60 * 60 * 100;
		today = System.currentTimeMillis();
		calendar = new GregorianCalendar(creditCard.getExpirationYear(), creditCard.getExpirationMonth(), 1);
		cardDate = calendar.getTimeInMillis();

		Assert.isTrue(cardDate > today + sevenDays, "creditCard.expired.error");
	}

	public String getMaskedCreditCardAsString(final CreditCard creditCard) {
		String number, mask;

		number = creditCard.getNumber();
		mask = "";
		if (number.length() <= 4)
			mask = number;
		else {
			for (int i = 0; i < number.length() - 4; i++)
				mask += "*";
			mask += number.substring(number.length() - 4);
		}

		return mask;
	}

	public void maskCreditCard(final CreditCard creditCard) {
		creditCard.setNumber(this.getMaskedCreditCardAsString(creditCard));
	}

	public CreditCard getCreditCardByChorbi() {
		CreditCard creditCard;
		Chorbi chorbi;
		chorbi = this.chorbiService.findChorbiByPrincipal();
		creditCard = this.creditCardRepository.findCreditCardByChorbiId(chorbi.getId());
		return creditCard;
	}

}
