
package services;

import java.util.Collection;

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

	public void deleteFromChorbi(final Chorbi chorbi) {
		CreditCard creditCard;

		creditCard = this.getCreditCardByChorbi();

		this.creditCardRepository.delete(creditCard);
	}

	// Other Bussiness Methods --------------------------------------------------------

	public CreditCard getCreditCardByChorbi() {
		CreditCard creditCard;
		Chorbi chorbi;

		chorbi = this.chorbiService.findChorbiByPrincipal();
		creditCard = this.getCreditCardByChorbi(chorbi);

		return creditCard;
	}

	public CreditCard getCreditCardByChorbi(final Chorbi chorbi) {
		CreditCard creditCard;

		creditCard = this.creditCardRepository.findCreditCardByChorbiId(chorbi.getId());

		return creditCard;
	}

}
