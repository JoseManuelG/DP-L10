
package controllers.chorbi;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChirpService;
import services.ChorbiService;
import services.LikesService;
import controllers.AbstractController;
import domain.Chorbi;

@Controller
@RequestMapping("/chorbi")
public class ViewChorbiController extends AbstractController {

	@Autowired
	private LikesService	likesService;

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ActorService	actorService;


	// List --------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Chorbi> chorbies;

		chorbies = this.chorbiService.findAll();

		result = new ModelAndView("chorbi/list");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/list.do");

		return result;
	}

	@RequestMapping(value = "/chorbi/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int chorbiId) {
		ModelAndView result;
		Chorbi chorbi;

		chorbi = this.chorbiService.findChorbiByPrincipal();

		result = new ModelAndView("chorbi/chorbi/view");
		result.addObject("chorbi", chorbi);

		result.addObject("requestURI", "chorbi/chorbi/view.do?chorbiId=" + chorbiId);

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Chorbi chorbi) {
		ModelAndView result;

		result = this.createEditModelAndView(chorbi, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Chorbi chorbi, final String message) {
		ModelAndView result;
		result = new ModelAndView("chorbi/chorbi/create");

		result.addObject("chorbi", chorbi);
		result.addObject("message", message);
		return result;
	}

}