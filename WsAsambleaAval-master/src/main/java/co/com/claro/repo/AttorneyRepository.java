package co.com.claro.repo;

import co.com.claro.entity.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttorneyRepository extends JpaRepository<Attorney, Long> {

    Optional<Attorney> findByTipIdAndNumId(String tipId, Long numId);

}
