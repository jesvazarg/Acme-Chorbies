
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select a from Chorbi c where c.userAccount.id = ?1")
	Chorbi findByUserAccountId(int userAccountId);

}
