
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	@Query("select c from Chirp c where c.sender.id=?1 and c.copy=false")
	Collection<Chirp> findChirpsSentByChorbiId(int chorbiId);

	@Query("select c from Chirp c where c.recipient.id=?1 and c.copy=true")
	Collection<Chirp> findChirpsReceivedByChorbiId(int chorbiId);

}
