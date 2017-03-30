
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.LoginService;
import domain.Administrator;
import forms.ActorForm;

@Service
@Transactional
public class AdministratorService {

	// Managed Repository --------------------------------------
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting Services --------------------------------------

	@Autowired
	private Validator				validator;


	//Simple CRUD methods-------------------------------------------------------------------
	public Administrator create() {
		final Administrator result = new Administrator();
		return result;
	}

	public Administrator save(final Administrator administrator) {
		Administrator result;

		Assert.notNull(administrator, "administrator.error.null");
		result = this.administratorRepository.save(administrator);
		Assert.notNull(result, "administrator.error.commit");

		return result;

	}

	// other business methods --------------------------------------

	public Administrator findAdministratorByPrincipal() {
		Administrator result;
		result = this.administratorRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public Administrator reconstruct(final ActorForm actorForm, final Administrator administrator, final BindingResult binding) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final Administrator result = new Administrator();
		//TODO descomentar cuando esté hecho el reconstruct de actorService
		//this.actorService.reconstruct(result, administrator, actorForm);
		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(actorForm.getUserAccount().getPassword(), null));
		return result;
	}

}
