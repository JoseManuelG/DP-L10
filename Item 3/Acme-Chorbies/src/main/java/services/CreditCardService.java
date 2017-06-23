
package services;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
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
	private ChorbiService			chorbiService;


	// Constructor --------------------------------------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------------------------

	public CreditCard create() {
		CreditCard result;
		Chorbi chorbi;

		result = new CreditCard();
		chorbi = this.chorbiService.findChorbiByPrincipal();
		result.setChorbi(chorbi);

		return result;
	}
	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard, "creditCard.null.error");
		Assert.isTrue(this.checkCreditCardByChorbi(creditCard), "creditCard.expired.error");
		if (creditCard.getId() != 0) {
			final CreditCard aux = this.creditCardRepository.findOne(creditCard.getId());
			final Chorbi principal = this.chorbiService.findChorbiByPrincipal();
			Assert.isTrue(aux.getChorbi().equals(principal), "creditCard.notYour");
		}

		CreditCard result;
		result = this.creditCardRepository.save(creditCard);
		return result;
	}
	public void delete(final CreditCard creditCard) {

		Assert.notNull(creditCard);
		Assert.isTrue(this.checkCreditCardByChorbi(creditCard), "creditCard.notYour");

		this.creditCardRepository.delete(creditCard);
		Assert.isTrue(!this.creditCardRepository.exists(creditCard.getId()));
	}

	public void deleteFromChorbi(final Chorbi chorbi) {
		CreditCard creditCard;

		creditCard = this.getCreditCardByChorbi(chorbi);
		if (creditCard != null)
			this.creditCardRepository.delete(creditCard);
	}

	// Other Bussiness Methods --------------------------------------------------------

	public CreditCard getCreditCardByChorbi() {
		CreditCard creditCard;
		Chorbi chorbi;
		chorbi = this.chorbiService.findChorbiByPrincipal();
		creditCard = this.creditCardRepository.findCreditCardByChorbiId(chorbi.getId());
		return creditCard;
	}

	public CreditCard getCreditCardByChorbi(final Chorbi chorbi) {
		CreditCard creditCard;

		creditCard = this.creditCardRepository.findCreditCardByChorbiId(chorbi.getId());

		return creditCard;
	}

	public boolean checkCreditCardByChorbi() {
		CreditCard creditCard;
		LocalDate expireTime, now;
		Period period;
		Boolean result;

		creditCard = this.getCreditCardByChorbi();
		//Añadido el bloque if por Roldan, porque si tenia una tarjeta de credito que caducaba el mes 12,  fallaba
		if (creditCard.getExpirationMonth() < 12)
			expireTime = new LocalDate(creditCard.getExpirationYear(), creditCard.getExpirationMonth() + 1, 1);
		else
			expireTime = new LocalDate(creditCard.getExpirationYear() + 1, 1, 1);
		now = new LocalDate();
		period = new Period(now, expireTime, PeriodType.yearMonthDay());

		result = period.getDays() > 1;

		return result;
	}
	public boolean checkCreditCardByChorbi(final CreditCard creditCard) {
		LocalDate expireTime, now;
		Period period;
		Boolean result;

		//Añadido el bloque if por Roldan, porque si tenia una tarjeta de credito que caducaba el mes 12,  fallaba
		if (creditCard.getExpirationMonth() < 12)
			expireTime = new LocalDate(creditCard.getExpirationYear(), creditCard.getExpirationMonth() + 1, 1);
		else
			expireTime = new LocalDate(creditCard.getExpirationYear() + 1, 1, 1);
		now = new LocalDate();
		period = new Period(now, expireTime, PeriodType.yearMonthDay());

		result = period.getDays() > 1;

		return result;
	}

	public void flush() {
		this.creditCardRepository.flush();

	}

}
