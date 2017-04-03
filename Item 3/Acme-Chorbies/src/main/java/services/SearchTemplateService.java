
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SearchTemplateRepository;
import domain.Chorbi;
import domain.Coordinates;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {

	// Managed Repository --------------------------------------
	@Autowired
	private SearchTemplateRepository	searchTemplateRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private ActorService				actorService;
	@Autowired
	private ChorbiService				chorbiService;
	@Autowired
	private ConfigurationService		configurationService;

	@Autowired
	private Validator					validator;


	// Simple CRUD methods --------------------------------------
	public SearchTemplate create() {
		SearchTemplate result;

		result = new SearchTemplate();
		result.setCacheMoment(new Date(System.currentTimeMillis() - this.configurationService.findConfiguration().getCachedTime()));
		result.setChorbies(new ArrayList<Chorbi>());

		return result;
	}
	public Collection<SearchTemplate> findAll() {
		Collection<SearchTemplate> result;

		result = this.searchTemplateRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SearchTemplate findOne(final int searchTemplateId) {
		SearchTemplate result;

		result = this.searchTemplateRepository.findOne(searchTemplateId);

		return result;
	}

	public SearchTemplate save(final SearchTemplate searchTemplate) {
		SearchTemplate result;
		final Collection<Chorbi> chorbies;
		Date timeOfCache, lastSearch;

		Assert.isTrue(this.chorbiService.findChorbiByPrincipal().equals(searchTemplate.getChorbi()));

		timeOfCache = new Date(System.currentTimeMillis() - this.configurationService.findConfiguration().getCachedTime());
		lastSearch = new Date(searchTemplate.getCacheMoment().getTime());
		//Revisar que el search guardado sea del Principal

		result = searchTemplate;
		//Comprobamos si el SearchTemplate ha sido modificado
		//TODO Probar a pasar el bloque if al FindOne() para que saliera actualizado automaticamente
		if (lastSearch.before(timeOfCache) || this.searchTemplateHasBeenModified(searchTemplate)) {

			result.setCacheMoment(new Date(System.currentTimeMillis() - 1000));

			//TODO montar query de busqueda

			chorbies = this.chorbiService.searchChorbis(searchTemplate.getDesiredRelationship(), searchTemplate.getGenre(),

			searchTemplate.getKeyword(), searchTemplate.getCoordinates().getCity(), searchTemplate.getCoordinates().getProvince(),

			searchTemplate.getCoordinates().getCountry(), searchTemplate.getCoordinates().getState(), searchTemplate.getAge());

			result.setChorbies(chorbies);

			result = this.searchTemplateRepository.save(result);
		}

		Assert.notNull(result);

		return result;
	}
	private boolean searchTemplateHasBeenModified(final SearchTemplate searchTemplate) {
		SearchTemplate old;
		boolean result;
		old = this.searchTemplateRepository.findOne(searchTemplate.getId());
		result = !searchTemplate.getAge().equals(old.getAge())

		|| !(searchTemplate.getDesiredRelationship().equals(old.getDesiredRelationship()))

		|| !(searchTemplate.getGenre().equals(old.getGenre()))

		|| !(searchTemplate.getKeyword().equals(old.getKeyword()))

		|| !(searchTemplate.getCoordinates().getCity().equals(old.getCoordinates().getCity()))

		|| !(searchTemplate.getCoordinates().getCountry().equals(old.getCoordinates().getCountry()))

		|| !(searchTemplate.getCoordinates().getProvince().equals(old.getCoordinates().getProvince()))

		|| !(searchTemplate.getCoordinates().getState().equals(old.getCoordinates().getState()));

		return result;

	}
	public void delete(final SearchTemplate searchTemplate) {
		Assert.notNull(searchTemplate, "searchTemplate.error.null");
		//Revisar que el search guardado sea del Principal
		Assert.isTrue(this.chorbiService.findChorbiByPrincipal().equals(searchTemplate.getChorbi()));

		Assert.isTrue(this.searchTemplateRepository.exists(searchTemplate.getId()), "searchTemplate.error.exists");

		this.searchTemplateRepository.delete(searchTemplate);
	}

	// Other business methods --------------------------------------

	public SearchTemplate findByPrincipal() {
		final SearchTemplate result;
		final int actorId = this.actorService.findActorByPrincipal().getId();
		result = this.searchTemplateRepository.findByChrobi(actorId);
		return result;
	}
	public SearchTemplate reconstruct(final SearchTemplate searchTemplate, final BindingResult binding) {
		SearchTemplate res, old;
		Integer auxAge = 18;
		old = this.findOne(searchTemplate.getId());
		if (searchTemplate.getAge() != null)
			auxAge = searchTemplate.getAge();
		res = this.create();
		//old things
		res.setId(old.getId());
		res.setVersion(old.getVersion());
		res.setCacheMoment(old.getCacheMoment());
		res.setChorbies(old.getChorbies());
		res.setChorbi(old.getChorbi());
		final Coordinates aux;
		aux = new Coordinates();
		aux.setId(old.getCoordinates().getId());
		aux.setVersion(old.getCoordinates().getVersion());
		aux.setCity(searchTemplate.getCoordinates().getCity());
		aux.setCountry(searchTemplate.getCoordinates().getCountry());
		aux.setProvince(searchTemplate.getCoordinates().getProvince());
		aux.setState(searchTemplate.getCoordinates().getState());

		res.setCoordinates(aux);
		//New things
		res.setDesiredRelationship(searchTemplate.getDesiredRelationship());
		res.setAge(auxAge);
		res.setGenre(searchTemplate.getGenre());
		res.setKeyword(searchTemplate.getKeyword());
		this.validator.validate(res, binding);

		return res;
	}
}
