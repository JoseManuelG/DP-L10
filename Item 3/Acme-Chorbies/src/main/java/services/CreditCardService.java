
package services;

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

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard, "La tarjeta de crédito no puede ser nula");
		CreditCard result;
		result = this.creditCardRepository.save(creditCard);
		return result;
	}

	public void delete(final CreditCard creditCard) {

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

}
