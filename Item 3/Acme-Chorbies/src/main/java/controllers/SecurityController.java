
package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.AdministratorService;
import services.ChorbiService;
import domain.Actor;
import domain.Administrator;
import domain.Chorbi;
import forms.ActorForm;

@Controller
@RequestMapping("/security")
public class SecurityController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		final ActorForm actorForm = new ActorForm();
		result = this.createRegisterModelAndView(actorForm);

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Chorbi chorbi = null;

		if (actorForm.getTypeOfActor().equals("CHORBI"))
			chorbi = this.chorbiService.reconstruct(actorForm, binding);
		if (binding.hasErrors())
			result = this.createRegisterModelAndView(actorForm);
		else if (!actorForm.getUserAccount().getPassword().equals(actorForm.getConfirmPassword()))
			result = this.createRegisterModelAndView(actorForm, "security.password.error");
		else if (!((boolean) actorForm.getAcepted()))
			result = this.createRegisterModelAndView(actorForm, "security.terms.error");
		else
			try {

				this.chorbiService.save(chorbi);

				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createRegisterModelAndView(actorForm, "chorbi.commit.error");
			}

		return result;
	}
	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Actor actor;
		Boolean isAdmin;
		ActorForm actorForm;

		actor = this.actorService.findActorByPrincipal();
		isAdmin = actor instanceof Administrator;

		actorForm = new ActorForm();
		actorForm.setName(actor.getName());
		actorForm.setSurname(actor.getSurname());
		actorForm.setEmail(actor.getEmail());
		actorForm.setPhone(actor.getPhone());
		actorForm.setUserAccount(actor.getUserAccount());

		result = this.createEditModelAndView(actorForm, isAdmin);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Actor principal, actorResult;
		Boolean isAdmin;
		String actorString;

		isAdmin = false;
		principal = this.actorService.findActorByPrincipal();
		if (principal instanceof Chorbi)
			actorResult = this.chorbiService.reconstruct(actorForm, (Chorbi) principal, binding);
		else {
			isAdmin = true;
			actorResult = this.administratorService.reconstruct(actorForm, (Administrator) principal, binding);
		}

		if (binding.hasErrors())
			result = this.createEditModelAndView(actorForm, isAdmin);
		else if (!actorForm.getUserAccount().getPassword().equals(actorForm.getConfirmPassword()))
			result = this.createEditModelAndView(actorForm, isAdmin, "security.password.error");
		else
			try {
				this.actorService.save(actorResult);
				actorString = principal.getClass().getSimpleName().toLowerCase();
				result = new ModelAndView("redirect:../" + actorString + "/myProfile.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actorForm, isAdmin, "lessor.commit.error");
			}

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ActorForm actorForm) {
		ModelAndView result;
		final Actor actor = this.actorService.findActorByPrincipal();
		Chorbi chorbi = null;

		final ArrayList<Authority> authorities = new ArrayList<Authority>();
		authorities.addAll(actor.getUserAccount().getAuthorities());
		final String aux = authorities.get(0).getAuthority();

		if (aux.equals(Authority.CHORBI))
			chorbi = this.chorbiService.findChorbiByPrincipal();
		try {

			if (aux.equals("CHORBI")) {
				//TODO A�ADIR TODO LO QUE SE BORRA CUANDO SE BORRA UN CHORBI

			}
			result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final Exception e) {
			result = this.createEditModelAndView(actorForm, false, "lessor.commit.error");
		}

		return result;

	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("security/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final Boolean isAdmin) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, isAdmin, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final Boolean isAdmin, final String message) {
		ModelAndView result;

		result = new ModelAndView("security/edit");
		result.addObject(actorForm);
		result.addObject("isAdmin", isAdmin);
		result.addObject("message", message);

		return result;
	}
}