
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id = ?1")
	Chorbi findByUserAccountId(int userAccountId);

	//A1: The minimum, the maximum, and the average number of chirps that a chor-bi receives from other chorbies.
	@Query("select min(c.reciveChirps.size), max(c.reciveChirps.size), avg(c.reciveChirps.size) from Chorbi c")
	Double[] minMaxAvgReciveChirps();

	//A3: The chorbies who have got more chirps.
	@Query("select c from Chorbi c where c.reciveChirps.size=(select max(c2.reciveChirps.size) from Chorbi c2)")
	Collection<Chorbi> findChorbiMoreReciveChirps();

	//A4: The chorbies who have sent more chirps.
	@Query("select c from Chorbi c where c.sentChirps.size=(select max(c2.sentChirps.size) from Chorbi c2)")
	Collection<Chorbi> findChorbiMoreSentChirps();

}
