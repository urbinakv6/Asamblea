package co.com.claro.repo;

import co.com.claro.entity.AttorneyXShareHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttorneyXShareholdeRepository extends JpaRepository<AttorneyXShareHolder, Long> {

    @Query("SELECT q.accNumAccion FROM AttorneyXShareHolder q WHERE q.apoNumId = :numId and q.apoTipId = :tipId")
    List<String> actionsByDoc( @Param("tipId") String tipId, @Param("numId") String numId);
}
