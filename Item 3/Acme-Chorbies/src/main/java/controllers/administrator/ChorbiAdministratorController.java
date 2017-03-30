
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import controllers.AbstractController;
import domain.Chorbi;

@Controller
@RequestMapping("/chorbi/administrator")
public class ChorbiAdministratorController extends AbstractController {

	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------

	public ChorbiAdministratorController() {
		super();
	}

	// Ban 	-------------------------------------------------------------------

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int chorbiId) {
		ModelAndView result;
		Chorbi chorbi;

		chorbi = this.chorbiService.findOne(chorbiId);

		this.chorbiService.banChorbi(chorbiId);
		//TODO averiguar formato url
		result = new ModelAndView("redirect:/chorbi/view.do?chorbiId=" + chorbi.getId());
		return result;
	}

	// Unban 	-------------------------------------------------------------------

	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int chorbiId) {
		ModelAndView result;
		Chorbi chorbi;

		chorbi = this.chorbiService.findOne(chorbiId);

		this.chorbiService.unbanChorbi(chorbiId);
		//TODO averiguar formato url
		result = new ModelAndView("redirect:/chorbi/view.do?chorbiId=" + chorbi.getId());
		return result;
	}

}
