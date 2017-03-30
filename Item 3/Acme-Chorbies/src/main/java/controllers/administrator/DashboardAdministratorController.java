
package controllers.administrator;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	public DashboardAdministratorController() {
		super();
	}


	private static DecimalFormat	df2	= new DecimalFormat(".##");


	// List --------------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("dashboard/administrator/dashboard");

		result.addObject("requestURI", "dashboard/administrator/dashboard.do");

		//Queries ChorbiService

		//result.addObject("chorbiesGroupedByCity",metododelservicio);
		//result.addObject("chorbiesGroupedByCountry",metododelservicio);
		//result.addObject("minumumChorbiAge",metododelservicio);
		//result.addObject("maximumChorbiAge",metododelservicio);
		//result.addObject("averageChorbiAge",metododelservicio);
		//result.addObject("ratioOfNoCCAndInvalidCCVersusValidCC",metododelservicio);

		//Queries SearchService

		//result.addObject("ratioActivitiesSearch"metododelservicio);
		//result.addObject("ratioFriendshipSearch"metododelservicio);
		//result.addObject("ratioLoveSearch"metododelservicio);

		//Queries LikeService

		//result.addObject("chorbiesOrderedByLikes",metododelservicio);
		//result.addObject("minimumChorbiLikes",metododelservicio);
		//result.addObject("maximumChorbiLikes",metododelservicio);
		//result.addObject("averageChorbiLikes",metododelservicio);

		//Queries ChirpService

		//result.addObject("minimumChirpsReceivedByChorbi",metododelservicio);
		//result.addObject("maximumChirpsReceivedByChorbi",metododelservicio);
		//result.addObject("averageChirpsReceivedByChorbi",metododelservicio);
		//result.addObject("minimumChirpsSentByChorbi",metododelservicio);
		//result.addObject("maximumChirpsSentByChorbi",metododelservicio);
		//result.addObject("averageChirpsSentByChorbi",metododelservicio);
		//result.addObject("ChorbiesWithMoreReceivedChirps",metododelservicio);
		//result.addObject("ChorbiesWithMoreSentChirps",metododelservicio);

		return result;
	}

}
