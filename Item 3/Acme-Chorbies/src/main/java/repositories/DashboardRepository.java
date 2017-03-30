
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
	@Query("select count(c), c.coordinates.country from Chorbi c group by c.coordinates.country")
	public List<Object[]> numberOfChorbiesPerCountry();

	// Dashboard - 01
	@Query("select count(c), c.coordinates.city from Chorbi c group by c.coordinates.city")
	public List<Object[]> numberOfChorbiesPerCity();

	// Dashboard - 02	
	@Query("select min(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Integer minAgeOfChorbies();

	// Dashboard - 02	
	@Query("select max(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Integer maxAgeOfChorbies();

	// Dashboard - 02	
	@Query("select avg(DATEDIFF(CURRENT_DATE, c.birthDate)/365.256363004) from Chorbi c")
	public Double averageAgeOfChorbies();

	// Dashboard - 03
	@Query("select count(c) from CreditCard c where c.expirationYear > YEAR(CURRENT_DATE) and c.expirationMonth >= MONTH(CURRENT_DATE)")
	public Double countValidCreditCard();

	// Dashboard - 04
	@Query("select count(c) from Chorbi c where c.desiredRelationship='activities'")
	public Double countChorbiesWhoDesireActivities();

	// Dashboard - 04
	@Query("select count(c) from Chorbi c where c.desiredRelationship='friendship'")
	public Double countChorbiesWhoDesireFriendship();

	// Dashboard - 04
	@Query("select count(c) from Chorbi c where c.desiredRelationship='love'")
	public Double countChorbiesWhoDesireLove();

	// Dashboard - 05
	@Query("select c from Likes l, Chorbi c where c=l.liked group by c order by count(c) desc")
	public List<Chorbi> findChorbiesOrderedByLikes();

	// Dashboard - 05
	@Query("select c from Chorbi c where c not in (select distinct l.liked from Likes l)")
	public List<Chorbi> findChorbiesWithoutLikes();

	//Dashboard - 06
	@Query("select count(l.liked) from Likes l group by l.liked order by count(l.liked) asc")
	public List<Double> minNumberOfLikesPerChorbi();

	//Dashboard - 06
	@Query("select count(l.liked) from Likes l group by l.liked order by count(l.liked) desc")
	public List<Double> maxNumberOfLikesPerChorbi();

	//Dashboard - 07
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null")
	public Double averageOfChirpsReceivedPerChorbi();

	//Dashboard - 07
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) asc")
	public List<Double> minOfChirpsReceivedPerChorbi();

	//Dashboard - 07
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) desc")
	public List<Double> maxOfChirpsReceivedPerChorbi();

	//Dashboard - 08
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null")
	public Double averageOfChirpsSentPerChorbi();

	//Dashboard - 08
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) asc")
	public Double minOfChirpsSentPerChorbi();

	//Dashboard - 08
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) desc")
	public Double maxOfChirpsSentPerChorbi();
}
