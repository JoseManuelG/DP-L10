
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

	//Find all the audits for a given property 
	@Query("select c from Likes c where liker.id=?1")
	Collection<Likes> findLikessByChorbiID(int id);

	//Find all the sended messages for a given chorbi
	@Query("select m from Likes m where m.liker.id=?1")
	public List<Likes> findSentLikesOfChorbi(int senderId);

	//Find all the received messages for a given chorbi
	@Query("select m from Likes m where m.liked.id=?1")
	public List<Likes> findReceivedLikesOfChorbi(int recipientId);

	//Find all the sended messages for a given chorbi
	@Query("select m from Likes m where m.liker.id=?1 and m.liked.id=?2")
	public Likes findUniqueLike(int likerId, int likedId);

}
