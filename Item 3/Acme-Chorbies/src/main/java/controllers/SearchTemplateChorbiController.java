
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SearchTemplateService;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/searchTemplate/chorbi")
public class SearchTemplateChorbiController extends AbstractController {

	@Autowired
	private SearchTemplateService	searchTemplateService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {

		ModelAndView result;
		Collection<Chorbi> results;
		SearchTemplate search;
		Date timeOfCache, lastSearch;

		search = this.searchTemplateService.findByPrincipal();

		lastSearch = new Date(search.getCacheMoment().getTime());
		timeOfCache = this.configurationService.findOne().getCachedTime();

		if (lastSearch.after(timeOfCache))
			results = search.getChorbies();
		else
			results = new ArrayList<Chorbi>();

		result = new ModelAndView("searchTemplate/chorbi/search");
		result.addObject("results", results);
		result.addObject("search", search);
		result.addObject("requestURI", "searchTemplate/chorbi/search.do");

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView search(final SearchTemplate search, final BindingResult binding) {
		ModelAndView result;
		SearchTemplate res;

		res = this.searchTemplateService.reconstruct(search, binding);
		Collection<Chorbi> results;
		if (binding.hasErrors()) {
			results = new ArrayList<Chorbi>();

			result = new ModelAndView("searchTemplate/chorbi/search");
			result.addObject("search", search);
			result.addObject("requestURI", "searchTemplate/chorbi/search.do");
			result.addObject("results", results);
		} else
			try {
				this.searchTemplateService.save(res);
				result = this.search();

			} catch (final Throwable oops) {
				results = new ArrayList<Chorbi>();

				result = new ModelAndView("searchTemplate/chorbi/search");
				result.addObject("search", search);
				result.addObject("requestURI", "searchTemplate/chorbi/search.do");
				result.addObject("results", results);
				result.addObject("message", "finder.commit.error");
			}

		return result;
	}
}
