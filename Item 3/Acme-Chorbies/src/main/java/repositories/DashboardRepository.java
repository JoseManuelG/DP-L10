
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	@Query(name = "select count(c), c.coordinates.country from Chorbi c group by c.coordinates.country")
	List<Object[]> numberOfChorbiesPerCountry();

	@Query(name = "select count(c), c.coordinates.country from Chorbi c group by c.coordinates.country")
	List<Object[]> numberOfChorbiesPerCity();
}
