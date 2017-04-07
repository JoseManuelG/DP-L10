
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	// Managed Repository --------------------------------------
	@Autowired
	private ActorRepository	actorRepository;


	// Supporting Services --------------------------------------

	/*
	 * @Autowired
	 * private CustomerService customerService;
	 * 
	 * @Autowired
	 * private AdministratorService administratorService;
	 */
	//Simple CRUD methods-------------------------------------------------------------------

	public Actor findOne(final int actorId) {
		return this.actorRepository.findOne(actorId);
	}
	/*
	 * public void save(final Actor actor) {
	 * if (actor instanceof Customer)
	 * this.customerService.save((Customer) actor);
	 * else if (actor instanceof Administrator)
	 * this.administratorService.save((Administrator) actor);
	 * 
	 * }
	 */
	public Collection<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Long count() {
		return this.actorRepository.count();
	}
	// other business methods --------------------------------------

	public Actor findActorByPrincipal() {
		Actor result;
		result = this.actorRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public void reconstruct(final Actor result, final Actor origin, final ActorForm actorForm) {
		UserAccount userAccount;

		userAccount = new UserAccount();
		// Setear lo que viene del formulario:

		userAccount.setPassword(actorForm.getUserAccount().getPassword());
		userAccount.setUsername(actorForm.getUserAccount().getUsername());

		result.setUserAccount(userAccount);
		result.setName(actorForm.getName());
		result.setSurname(actorForm.getSurname());
		result.setEmail(actorForm.getEmail());
		result.setPhone(actorForm.getPhone());

		// Setear lo que no viene del formulario:

		userAccount.setId(origin.getUserAccount().getId());
		userAccount.setVersion(origin.getUserAccount().getVersion());
		userAccount.setAuthorities(origin.getUserAccount().getAuthorities());
		userAccount.setEnabled(origin.getUserAccount().isEnabled());

		result.setId(origin.getId());
		result.setVersion(origin.getVersion());

	}
	public void save(final Actor actorResult) {
		this.actorRepository.save(actorResult);
	}

}
