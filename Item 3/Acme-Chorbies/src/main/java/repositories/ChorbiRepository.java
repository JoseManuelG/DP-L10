
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id = ?1")
	Chorbi findByUserAccountId(int id);
	@Query("	 select c from Chorbi c where c.desiredRelationship like concat(?1) and c.genre like concat( ?2) and c.description like concat('%', ?3, '%')")
	Collection<Chorbi> searchChorbis(String desiredRelathionship, String genre, String keyword);
}
