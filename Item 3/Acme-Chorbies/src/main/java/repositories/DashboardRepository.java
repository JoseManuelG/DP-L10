
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	// Dashboard - 01
	@Query(name = "select count(c), c.coordinates.country from Chorbi c group by c.coordinates.country")
	List<Object[]> numberOfChorbiesPerCountry();

	@Query(name = "select count(c), c.coordinates.city from Chorbi c group by c.coordinates.city")
	List<Object[]> numberOfChorbiesPerCity();

	// Dashboard - 02	
	@Query(name = "select min(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	Integer minAgeOfChorbies();

	@Query(name = "select max(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	Integer maxAgeOfChorbies();

	@Query(name = "select avg(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	Double averageAgeOfChorbies();

	// Dashboard - 03
	@Query(name = "select count(c) from CreditCard c where c.expirationYear > YEAR(CURRENT_DATE) and c.expirationMonth >= MONTH(CURRENT_DATE)")
	Double countValidCreditCard();

}
