
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiRepository;
import repositories.DashboardRepository;
import domain.Chorbi;

@Service
@Transactional
public class DashboardService {

	// Managed Repository --------------------------------------
	@Autowired
	private DashboardRepository	dashboardRepository;

	@Autowired
	private ChorbiRepository	chorbiRepository;


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
		List<Chorbi> res;

		res = this.dashboardRepository.findChorbiesOrderedByLikes();
		res.addAll(this.dashboardRepository.findChorbiesWithoutLikes());

		return res;
	}

}
