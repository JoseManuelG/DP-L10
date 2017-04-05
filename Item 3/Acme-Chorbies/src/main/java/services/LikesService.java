
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.LikesRepository;
import security.LoginService;
import domain.Chorbi;
import domain.Likes;

@Service
@Transactional
public class LikesService {

	//Managed Repository-----------------------------

	@Autowired
	private LikesRepository	likesRepository;

	//Supporting services-----------------------------

	@Autowired
	private LoginService	loginService;

	@Autowired
	private ChorbiService	chorbiService;


	//Constructors------------------------------------

	public LikesService() {
		super();
	}

	//Simple CRUD methods----------------------------

	public Likes create(final Chorbi chorbi) {
		Likes result;
		Chorbi principal;

		principal = this.chorbiService.findChorbiByPrincipal();

		result = new Likes();
		result.setLiked(chorbi);
		result.setLiker(principal);

		return result;
	}

	public Collection<Likes> findAll() {
		Collection<Likes> result;

		result = this.likesRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Likes findOne(final int likeId) {
		Likes result;

		result = this.likesRepository.findOne(likeId);
		Assert.notNull(result);

		return result;
	}

	@SuppressWarnings("static-access")
	public Likes save(final Likes likes) {
		final Date currentMoment = new Date(System.currentTimeMillis() - 10000);

		Assert.notNull(likes, "El like no puede ser nulo");
		Assert.isTrue(likes.getId() == 0, "No se pueden modificar likes");
		likes.setMoment(currentMoment);

		Assert.isTrue(likes.getLiker().getUserAccount().equals(this.loginService.getPrincipal()), "Solo el propietario puede realizar operaciones");
		Likes result;

		result = this.likesRepository.save(likes);

		return result;
	}

	public void delete(final Likes likes) {
		Assert.notNull(likes, "El attachment no puede ser nulo");
		Assert.isTrue(likes.getId() != 0, "El attachment debe estar antes en la base de datos");

		this.likesRepository.exists(likes.getId());
		Assert.isTrue(this.chorbiService.findChorbiByPrincipal().equals(likes.getLiker()) || this.chorbiService.findChorbiByPrincipal().equals(likes.getLiked()));

		this.likesRepository.delete(likes);

	}

	//Other bussiness methods------------------------

	public Collection<Likes> findAllLikessOfAChorbi(final Chorbi chorbi) {
		Collection<Likes> likes;
		this.likesRepository.findAll();
		likes = this.likesRepository.findLikessByChorbiID(chorbi.getId());
		return likes;
	}

	public boolean validAutoLikes(final Likes like) {
		boolean result = false;
		if (like.getLiker().equals(like.getLiked()))
			result = true;
		return result;

	}

	public Likes reconstruct(final Likes likes, final BindingResult binding) {
		Likes result;
		result = new Likes();

		// Setear lo que viene del formulario:
		result.setComment(likes.getComment());
		result.setLiked(likes.getLiked());
		result.setLiker(likes.getLiker());

		// Setear lo que no viene del formulario:
		result.setMoment(likes.getMoment());

		result.setId(likes.getId());
		result.setVersion(likes.getVersion());

		return result;
	}

	public Collection<Likes> findReceivedLikesOfPrincipal() {
		final int recipientId = this.chorbiService.findChorbiByPrincipal().getId();
		final List<Likes> result = this.likesRepository.findReceivedLikesOfChorbi(recipientId);
		return result;
	}

	public Collection<Likes> findSentLikesOfPrincipal() {
		final int senderId = this.chorbiService.findChorbiByPrincipal().getId();
		final List<Likes> result = this.likesRepository.findSentLikesOfChorbi(senderId);
		return result;
	}

}
