
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiRepository;
import repositories.DashboardRepository;
import repositories.LikesRepository;
import domain.Chorbi;

@Service
@Transactional
public class DashboardService {

	// Managed Repository --------------------------------------
	@Autowired
	private DashboardRepository	dashboardRepository;

	@Autowired
	private ChorbiRepository	chorbiRepository;

	@Autowired
	private LikesRepository		likesRepository;


	//Dashboard - 01
	public List<Object[]> numberOfChorbiesPerCountry() {
		return this.dashboardRepository.numberOfChorbiesPerCountry();
	}

	//Dashboard - 01
	public List<Object[]> numberOfChorbiesPerCity() {
		return this.dashboardRepository.numberOfChorbiesPerCity();
	}

	//Dashboard - 02
	public Integer minAgeOfChorbies() {
		return this.dashboardRepository.minAgeOfChorbies();
	}

	//Dashboard - 02
	public Integer maxAgeOfChorbies() {
		return this.dashboardRepository.maxAgeOfChorbies();
	}

	//Dashboard - 02
	public Double averageAgeOfChorbies() {
		return this.dashboardRepository.averageAgeOfChorbies();
	}

	//Dashboard - 03
	public Double ratioChorbiesWithoutValidCreditCard() {
		Long chorbies;
		Double res;

		chorbies = this.chorbiRepository.count();

		if (chorbies > 0)
			res = this.dashboardRepository.countValidCreditCard() / chorbies;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 04
	public Double ratioChorbiesWhoDesireActivities() {
		Long chorbies;
		Double res;

		chorbies = this.chorbiRepository.count();

		if (chorbies > 0)
			res = this.dashboardRepository.countChorbiesWhoDesireActivities() / chorbies;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 04
	public Double ratioChorbiesWhoDesireFriendship() {
		Long chorbies;
		Double res;

		chorbies = this.chorbiRepository.count();

		if (chorbies > 0)
			res = this.dashboardRepository.countChorbiesWhoDesireFriendship() / chorbies;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 04
	public Double ratioChorbiesWhoDesireLove() {
		Long chorbies;
		Double res;

		chorbies = this.chorbiRepository.count();

		if (chorbies > 0)
			res = this.dashboardRepository.countChorbiesWhoDesireLove() / chorbies;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 05
	public List<Chorbi> chorbiesOrderedByLikes() {
		final List<Chorbi> res;

		res = new ArrayList<Chorbi>();
		res.addAll(this.dashboardRepository.findChorbiesOrderedByLikes());
		res.addAll(this.dashboardRepository.findChorbiesWithoutLikes());

		return res;
	}

	//Dashboard - 06
	public Integer minNumberOfLikesPerChorbi() {
		Long chorbies;
		List<Double> counts;
		Double res;

		counts = new ArrayList<Double>();

		chorbies = this.chorbiRepository.count();
		counts.addAll(this.chorbiRepository.minNumberOfLikesPerChorbi());

		if (chorbies > 0 && counts.size() == chorbies)
			res = counts.get(0);
		else
			res = 0.;

		return res.intValue();
	}

	//Dashboard - 06
	public Integer maxNumberOfLikesPerChorbi() {
		List<Double> counts;
		Double res;

		counts = new ArrayList<Double>();
		counts.addAll(this.chorbiRepository.maxNumberOfLikesPerChorbi());

		if (counts.size() > 0)
			res = counts.get(0);
		else
			res = 0.;

		return res.intValue();
	}

	//Dashboard - 06
	public Double avgNumberOfLikesPerChorbi() {
		Long chorbies, likes;
		Double res;

		chorbies = this.chorbiRepository.count();
		likes = this.likesRepository.count();

		if (chorbies > 0)
			res = 1.0 * likes / chorbies;
		else
			res = 0.;

		return res;
	}

}
