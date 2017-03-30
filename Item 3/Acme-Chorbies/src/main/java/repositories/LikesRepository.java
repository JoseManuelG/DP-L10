
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

	//Find all the audits for a given property 
	@Query("select c from Likes c where recipient_id=?1")
	Collection<Likes> findLikessByChorbiID(int id);

}
