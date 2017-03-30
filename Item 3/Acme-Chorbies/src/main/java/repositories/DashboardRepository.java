
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

	// Dashboard - 04
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='activities'")
	Double countChorbiesWhoDesiredActivities();
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='friendship'")
	Double countChorbiesWhoDesiredFriendship();
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='love'")
	Double countChorbiesWhoDesiredLove();
	
	// Dashboard - 05
	@Query(name = "select c from Likes l, Chorbi c where c=l.liked group by c order by count(c) desc;
	//habria que añadirle los chorbies que no estan en ningun likes que te los da la siguiente query
	@Query(name = "select c from Chorbi c where c not in (select distinct l.liked from Likes l);
	
}
