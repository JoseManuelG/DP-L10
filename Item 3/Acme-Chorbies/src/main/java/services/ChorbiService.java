
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Chorbi;
import forms.ActorForm;

@Service
@Transactional
public class ChorbiService {

	//Managed Repository--------------------------------------------------------------------

	@Autowired
	private ChorbiRepository		chorbiRepository;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	// Supporting Services --------------------------------------

	@Autowired
	private Validator				validator;

	@Autowired
	private ChirpService			chirpService;

	@Autowired
	private LikesService			likesService;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private ActorService			actorService;


	//Simple CRUD methods-------------------------------------------------------------------
	public Chorbi create() {
		final Chorbi result = new Chorbi();
		return result;
	}

	public Chorbi save(final Chorbi chorbi) {
		Chorbi result;

		Assert.notNull(chorbi, "chorbi.error.null");
		chorbi.setUserAccount(this.userAccountRepository.save(chorbi.getUserAccount()));
		result = this.chorbiRepository.save(chorbi);
		Assert.notNull(result, "chorbi.error.commit");

		return result;

	}
	public Chorbi findOne(final int id) {
		Chorbi result;
		result = this.chorbiRepository.findOne(id);
		return result;
	}

	public Collection<Chorbi> findAll() {
		Collection<Chorbi> result;
		result = this.chorbiRepository.findAll();
		return result;
	}

	public Long count() {
		return this.chorbiRepository.count();
	}

	public void delete() {
		final Chorbi chorbi = this.findChorbiByPrincipal();

		this.chorbiRepository.delete(chorbi);
		this.userAccountRepository.delete(chorbi.getUserAccount().getId());

	}
	public void flush() {
		this.chorbiRepository.flush();
	}
	//Other Business methods-------------------------------------------------------------------

	public Chorbi findChorbiByPrincipal() {
		Chorbi result;
		result = this.chorbiRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public Chorbi reconstruct(final ActorForm actorForm, final BindingResult binding) {
		final Chorbi result = this.create();

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount userAccount = new UserAccount();
		userAccount.setUsername(actorForm.getUserAccount().getUsername());
		userAccount.setPassword(actorForm.getUserAccount().getPassword());
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority("CHORBI");
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setName(actorForm.getName());
		result.setSurname(actorForm.getSurname());
		result.setEmail(actorForm.getEmail());
		result.setPhone(actorForm.getPhone());

		result.setUserAccount(userAccount);

		this.validator.validate(result, binding);
		userAccount.setPassword(encoder.encodePassword(actorForm.getUserAccount().getPassword(), null));
		return result;
	}

	public Chorbi reconstruct(final ActorForm actorForm, final Chorbi chorbi, final BindingResult binding) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		Chorbi result;

		result = new Chorbi();

		this.actorService.reconstruct(result, chorbi, actorForm);

		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(actorForm.getUserAccount().getPassword(), null));
		return result;
	}

	public void banChorbi(final int chorbiId) {
		Chorbi chorbi;
		chorbi = this.chorbiRepository.findOne(chorbiId);
		chorbi.setBanned(true);
		this.chorbiRepository.save(chorbi);
	}

	public void unbanChorbi(final int chorbiId) {
		Chorbi chorbi;
		chorbi = this.chorbiRepository.findOne(chorbiId);
		chorbi.setBanned(false);
		this.chorbiRepository.save(chorbi);
	}
	public Collection<Chorbi> searchChorbis(final String desiredRelathionship, final String genre, final String keyword, final String cityCoordinate, final String provinceCoordinate, final String countryCoordinate, final String stateCoordinate,
		final Integer age) {

		final Date aux = new Date(System.currentTimeMillis());
		final Date aux2 = new Date(aux.getYear() - age, aux.getMonth(), aux.getDay());
		final Date firstDate = new Date(aux2.getYear() - 5, aux.getMonth(), aux.getDay());
		final Date secondDate = new Date(aux2.getYear() + 5, aux.getMonth(), aux.getDay());

		final Collection<Chorbi> res = this.chorbiRepository.searchChorbis(desiredRelathionship, genre, keyword, cityCoordinate, provinceCoordinate, countryCoordinate, stateCoordinate, firstDate, secondDate);
		return res;
	}
	public Collection<Chorbi> searchChorbisWithoutAge(final String desiredRelathionship, final String genre, final String keyword, final String cityCoordinate, final String provinceCoordinate, final String countryCoordinate, final String stateCoordinate) {

		final Collection<Chorbi> res = this.chorbiRepository.searchChorbisWithoutAge(desiredRelathionship, genre, keyword, cityCoordinate, provinceCoordinate, countryCoordinate, stateCoordinate);
		return res;
	}

}
