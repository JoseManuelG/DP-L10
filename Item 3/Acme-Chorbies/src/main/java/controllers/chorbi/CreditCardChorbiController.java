
package controllers.chorbi;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.CreditCardService;
import controllers.AbstractController;
import domain.Chorbi;
import domain.CreditCard;

@Controller
@RequestMapping("/creditCard/lessor")
public class CreditCardChorbiController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CreditCardService	creditCardService;

	@Autowired
	private ChorbiService		chorbiService;


	// Constructors -----------------------------------------------------------

	public CreditCardChorbiController() {
		super();
	}

	// Create --------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final CreditCard creditCard = this.creditCardService.create();
		final Chorbi chorbi = this.chorbiService.findChorbiByPrincipal();
		creditCard.setChorbi(chorbi);
		result = this.createEditModelAndView(creditCard);
		return result;

	}
	// View ---------------------------------------------------------------

	@RequestMapping(value = "/myCreditCard", method = RequestMethod.GET)
	public ModelAndView view() {
		final ModelAndView result;
		CreditCard creditCard;
		creditCard = this.creditCardService.getCreditCardByChorbi();
		result = new ModelAndView("creditCard/chorbi/view");
		final Chorbi chorbi = this.chorbiService.findChorbiByPrincipal();
		if (creditCard != null)
			result.addObject("creditCard", creditCard);
		else
			result.addObject("editable", Boolean.FALSE);
		return result;
	}
	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		CreditCard creditCard;
		creditCard = this.creditCardService.getCreditCardByChorbi();
		result = this.createEditModelAndView(creditCard);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public @ResponseBody
	ModelAndView save(@Valid final CreditCard creditCard, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = this.createEditModelAndView(creditCard);
		} else
			try {
				this.creditCardService.save(creditCard);
				result = new ModelAndView("redirect:../lessor/myCreditCard.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(creditCard, "creditCard.commit.error");
			}

		return result;
	}

	// Delete ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final CreditCard creditCard, final BindingResult binding) {
		ModelAndView result;

		Assert.notNull(creditCard);
		System.out.println(binding);
		try {
			this.creditCardService.delete(creditCard);
			result = new ModelAndView("redirect:../lessor/myCreditCard.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(creditCard, "creditCard.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final CreditCard creditCard) {
		ModelAndView result;

		result = this.createEditModelAndView(creditCard, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CreditCard creditCard, final String message) {
		ModelAndView result;
		result = new ModelAndView("creditCard/lessor/edit");
		result.addObject("creditCard", creditCard);
		result.addObject("message", message);
		return result;
	}

}