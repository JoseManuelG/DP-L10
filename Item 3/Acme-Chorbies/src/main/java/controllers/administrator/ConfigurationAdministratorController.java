
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import controllers.AbstractController;
import domain.Configuration;
import forms.ConfigurationForm;

@Controller
@RequestMapping("/configuration/administrator")
public class ConfigurationAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ConfigurationService	configurationService;


	// Constructors -----------------------------------------------------------

	public ConfigurationAdministratorController() {
		super();
	}

	// Edit -----------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final Configuration configuration = this.configurationService.findConfiguration();
		final ModelAndView result = this.createEditModelAndView(configuration);
		return result;

	}

	// Save ---------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ConfigurationForm configurationForm, final BindingResult binding) {
		ModelAndView result;
		Configuration configuration;

		configuration = this.configurationService.reconstruct(configurationForm, binding);
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(configuration);
			System.out.println(binding.getAllErrors().toString());
		} else
			try {
				this.configurationService.save(configuration);
				result = new ModelAndView("redirect:../../");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(configuration, "configuration.commit.error");
			}
		return result;
	}

	// Ancillary Methods ----------------------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Configuration configuration) {
		ModelAndView result;

		result = this.createEditModelAndView(configuration, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Configuration configuration, final String message) {
		ModelAndView result;
		result = new ModelAndView("configuration/administrator/edit");

		result.addObject("configuration", configuration);
		result.addObject("message", message);
		result.addObject("requestURI", "configuration/administrator/edit.do");
		return result;
	}

}
