
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;
import domain.Chorbi;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	//Find all the sended messages for a given chorbi
	@Query("select m from Chirp m where m.sender.id=?1 and m.isSender=true")
	public List<Chirp> findSentChirpOfChorbi(int senderId);

	//Find all the received messages for a given chorbi
	@Query("select m from Chirp m where m.recipient.id=?1 and m.isSender=false")
	public List<Chirp> findReceivedChirpOfChorbi(int recipientId);

	//Find all the sended CopyChirps for a given chorbi
	@Query("select m from Chirp m where m.sender.id=?1 and m.isSender=false")
	public List<Chirp> findSentChirpOfChorbi2(int senderId);

	//Find all the received CopyChirps for a given chorbi
	@Query("select m from Chirp m where m.recipient.id=?1 and m.isSender=true")
	public List<Chirp> findReceivedChirpOfChorbi2(int recipientId);

	//No probado en vista, pero por queryDataBase.java funciona
	//Find all messages for a given chorbi
	@Query("select m from Chirp m where m.recipient.id=?1 or m.sender.id=?1")
	public List<Chirp> findAllChirpOfChorbi(int ChorbiId);

	//09 - The minimum, the average, and the maximum number of messages sent per chorbi. Part1
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null")
	Double avgChirpsSentPerChorbi();

	//09 - The minimum, the average, and the maximum number of messages sent per chorbi. Part2
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) asc")
	List<Long> minChirpsSentPerChorbi();

	//09 - The minimum, the average, and the maximum number of messages sent per chorbi. Part2
	@Query("select count(m) from Chirp m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) desc")
	List<Long> maxChirpsSentPerChorbi();

	//10 - The minimum, the average, and the maximum number of messages received per chorbi. Part1
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null")
	Double avgChirpsReceivedPerChorbi();

	//10 - The minimum, the average, and the maximum number of messages received per chorbi. Part2
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) asc")
	List<Long> minChirpsReceivedPerChorbi();

	//10 - The minimum, the average, and the maximum number of messages received per chorbi. Part2
	@Query("select count(m) from Chirp m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) desc")
	List<Long> maxChirpsReceivedPerChorbi();

	//11 - The chorbis who have sent more messages.
	@Query("select m.sender from Chirp m where m.isSender=true and m.sender is not null group by m.sender order by count(m) desc")
	List<Chorbi> chorbiSentMoreChirps();

	//12 - The chorbis who have received more messages.
	@Query("select m.recipient from Chirp m where m.isSender=false and m.recipient is not null group by m.recipient order by count(m) desc")
	List<Chorbi> chorbiReceivedMoreChirps();

}
