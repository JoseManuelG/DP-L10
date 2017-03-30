
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Chorbi;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	// Dashboard - 01
	@Query(name = "select count(c), c.coordinates.country from Chorbi c group by c.coordinates.country")
	public List<Object[]> numberOfChorbiesPerCountry();

	// Dashboard - 01
	@Query(name = "select count(c), c.coordinates.city from Chorbi c group by c.coordinates.city")
	public List<Object[]> numberOfChorbiesPerCity();

	// Dashboard - 02	
	@Query(name = "select min(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Integer minAgeOfChorbies();

	// Dashboard - 02	
	@Query(name = "select max(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Integer maxAgeOfChorbies();

	// Dashboard - 02	
	@Query(name = "select avg(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Double averageAgeOfChorbies();

	// Dashboard - 03
	@Query(name = "select count(c) from CreditCard c where c.expirationYear > YEAR(CURRENT_DATE) and c.expirationMonth >= MONTH(CURRENT_DATE)")
	public Double countValidCreditCard();

	// Dashboard - 04
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='activities'")
	public Double countChorbiesWhoDesireActivities();

	// Dashboard - 04
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='friendship'")
	public Double countChorbiesWhoDesireFriendship();

	// Dashboard - 04
	@Query(name = "select count(c) from Chorbi c where c.desiredRelationship='love'")
	public Double countChorbiesWhoDesireLove();

	// Dashboard - 05
	@Query(name = "select c from Likes l, Chorbi c where c=l.liked group by c order by count(c) desc")
	public List<Chorbi> findChorbiesOrderedByLikes();

	// Dashboard - 05
	@Query(name = "select c from Chorbi c where c not in (select distinct l.liked from Likes l)")
	public List<Chorbi> findChorbiesWithoutLikes();

	//Dashboard - 06
	@Query(name = "select count(l.liked) from Likes l group by l.liked order by count(l.liked) asc")
	public Double minNumberOfLikesPerChorbi();
	//se hace un count de chorbies y si es mayor que el size de la query de arriba, el minimo es 0, si no es el primer elemento que devuelve

	//Dashboard - 06
	@Query(name = "select count(l.liked) from Likes l group by l.liked order by count(l.liked) desc")
	public Double maxNumberOfLikesPerChorbi();
	//el maximo es el primer elemento que devuelve

}
