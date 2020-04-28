package co.com.claro.repo;

import co.com.claro.entity.ShareHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareHolderRepository extends JpaRepository<ShareHolder, Long> {
    Optional<ShareHolder> findByTipIdAndNumIdAndNumeroAccion(String tipId, String numId, String numeroAccion);
    Optional<ShareHolder> findByNumeroAccion(String numeroAccion);
    Optional<ShareHolder> findByTipIdAndNumId(String tipId, String numId);

    @Query("select q.numeroAccion from ShareHolder q where q.tipId = :tipId and  q.numId = :numId")
    List<String> findAllActionsByDocument(@Param("tipId") String tipId, @Param("numId") String numId);
}
