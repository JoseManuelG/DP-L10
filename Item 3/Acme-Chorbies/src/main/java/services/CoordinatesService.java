
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CoordinatesRepository;
import domain.Coordinates;

@Service
@Transactional
public class CoordinatesService {

	//Managed Repository--------------------------------------------------------------------

	@Autowired
	private CoordinatesRepository	coordinatesRepository;

	// Supporting Services --------------------------------------

	@Autowired
	private ChorbiService			chorbiService;


	//Simple CRUD methods-------------------------------------------------------------------
	public Coordinates create() {
		final Coordinates result = new Coordinates();
		result.setCity("");
		result.setCountry("");
		result.setProvince("");
		result.setState("");
		return result;
	}

	public Coordinates save(final Coordinates coordinates) {
		Coordinates result;

		Assert.notNull(coordinates, "coordinates.error.null");

		//		Assert.isTrue(!coordinates.getCity().isEmpty(), "coordinates.error.empty.city");

		result = this.coordinatesRepository.save(coordinates);

		Assert.notNull(result, "coordinates.error.commit");

		return result;

	}
	public Coordinates findOne(final int id) {
		Coordinates result;
		result = this.coordinatesRepository.findOne(id);
		return result;
	}

	public Collection<Coordinates> findAll() {
		Collection<Coordinates> result;
		result = this.coordinatesRepository.findAll();
		return result;
	}

	public Long count() {
		return this.coordinatesRepository.count();
	}

	public void delete() {
		final Coordinates coordinates = this.chorbiService.findCoordinatesByUserAccount();

		this.coordinatesRepository.delete(coordinates);
	}

	public void delete(final Coordinates coordinates) {

		this.coordinatesRepository.delete(coordinates);

	}

	public void flush() {
		this.coordinatesRepository.flush();
	}
	//Other Business methods-------------------------------------------------------------------

}
