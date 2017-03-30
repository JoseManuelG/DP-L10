
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

	public Likes create() {
		Likes result;

		result = new Likes();

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
		Assert.notNull(likes, "El comentario no puede ser nulo");
		Assert.isTrue(likes.getId() == 0, "No se pueden modificar comentarios");

		Assert.isTrue(likes.getLiker().getUserAccount().equals(this.loginService.getPrincipal()), "Solo el propietario puede realizar operaciones");
		Likes result;

		result = this.likesRepository.save(likes);

		return result;
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

}
