package co.com.claro.repo;

import co.com.claro.entity.QuestionXAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionXActionRepository extends JpaRepository<QuestionXAction, Long> {
}
