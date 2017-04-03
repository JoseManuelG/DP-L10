
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id = ?1")
	Chorbi findByUserAccountId(int id);

	@Query("select c from Chorbi c where c.desiredRelationship like concat('%',?1,'%') and c.genre like concat('%',?2,'%') and c.description like concat('%', ?3, '%') "
		+ "and c.coordinates.city like concat('%', ?4, '%')  and c.coordinates.province like concat('%', ?5, '%') and c.coordinates.country like concat('%', ?6, '%') and c.coordinates.state like concat('%', ?7, '%') and c.coordinates.state like concat('%', ?7, '%') and ?8 <=c.birthDate and ?9 >= c.birthDate")
	Collection<Chorbi> searchChorbis(String desiredRelathionship, String genre, String keyword, String cityCoordinate, String provinceCoordinate, String countryCoordinate, String stateCoordinate, Date firstDate, Date SecondDate);
	@Query("select c from Chorbi c where c.desiredRelationship like concat('%',?1,'%') and c.genre like concat('%',?2,'%') and c.description like concat('%', ?3, '%') "
		+ "and c.coordinates.city like concat('%', ?4, '%')  and c.coordinates.province like concat('%', ?5, '%') and c.coordinates.country like concat('%', ?6, '%') and c.coordinates.state like concat('%', ?7, '%') and c.coordinates.state like concat('%', ?7, '%') ")
	Collection<Chorbi> searchChorbisWithoutAge(String desiredRelathionship, String genre, String keyword, String cityCoordinate, String provinceCoordinate, String countryCoordinate, String stateCoordinate);

}
