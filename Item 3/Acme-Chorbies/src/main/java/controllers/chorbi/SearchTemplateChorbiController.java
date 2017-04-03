
package controllers.chorbi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import services.SearchTemplateService;
import controllers.AbstractController;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/searchTemplate/chorbi")
public class SearchTemplateChorbiController extends AbstractController {

	@Autowired
	private SearchTemplateService	searchTemplateService;
	@Autowired
	private ConfigurationService	configurationService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {

		final Collection<String> genres = new ArrayList<String>();
		genres.add("all");
		genres.add("man");
		genres.add("woman");
		final ArrayList<String> relation = new ArrayList<String>();
		relation.add("all");
		relation.add("activities");
		relation.add("friendship");
		relation.add("love");

		ModelAndView result;
		Collection<Chorbi> results;
		SearchTemplate search;
		Date timeOfCache, lastSearch;

		search = this.searchTemplateService.findByPrincipal();
		//revisa tiempo de cacheo
		lastSearch = new Date(search.getCacheMoment().getTime());
		timeOfCache = new Date(this.configurationService.findConfiguration().getCachedTime());

		if (lastSearch.after(timeOfCache))
			results = search.getChorbies();
		else
			results = new ArrayList<Chorbi>();

		if (search.getAge().equals(0))
			search.setAge(null);

		result = new ModelAndView("searchTemplate/chorbi/search.do");
		result.addObject("results", results);
		result.addObject("genres", genres);
		result.addObject("relation", relation);
		result.addObject("search", search);
		result.addObject("requestURI", "searchTemplate/chorbi/search.do");

		return result;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView search(final SearchTemplate search, final BindingResult binding) {
		ModelAndView result;
		SearchTemplate res;
		final Collection<String> genres = new ArrayList<String>();
		genres.add("all");
		genres.add("man");
		genres.add("woman");
		final ArrayList<String> relation = new ArrayList<String>();
		relation.add("all");
		relation.add("activities");
		relation.add("friendship");
		relation.add("love");

		res = this.searchTemplateService.reconstruct(search, binding);
		Collection<Chorbi> results;
		if (binding.hasErrors()) {
			results = new ArrayList<Chorbi>();

			result = new ModelAndView("searchTemplate/chorbi/search.do");
			result.addObject("search", search);
			result.addObject("requestURI", "searchTemplate/chorbi/search.do");
			result.addObject("results", results);
		} else
			try {
				this.searchTemplateService.save(res);
				result = this.search();

			} catch (final Throwable oops) {
				results = new ArrayList<Chorbi>();

				result = new ModelAndView("searchTemplate/chorbi/search.do");
				result.addObject("search", search);
				result.addObject("requestURI", "searchTemplate/chorbi/search.do");
				result.addObject("results", results);
				result.addObject("genres", genres);
				result.addObject("relation", relation);
				result.addObject("message", "searchTemplate.commit.error");
			}

		return result;
	}
}
