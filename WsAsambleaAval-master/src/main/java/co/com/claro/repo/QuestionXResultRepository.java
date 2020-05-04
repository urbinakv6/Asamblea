package co.com.claro.repo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import co.com.claro.entity.QuestionXResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class QuestionXResultRepository {
	
	@Value("${db.question.result.sql}")
	private String sql;
	
	@PersistenceContext
	EntityManager em;
	
	public List<QuestionXResult> findAllResults() {
		
		log.info("process=get-QuestionXResultRepository");
		List<QuestionXResult> results = new ArrayList<>();
		
		for (Object objeto : em.createNativeQuery(sql).getResultList()) {
			results.add(new QuestionXResult(
					(BigInteger) ((Object[]) objeto)[0],
					(String) ((Object[]) objeto)[1],
					(BigInteger) ((Object[]) objeto)[2],
					(BigInteger) ((Object[]) objeto)[3],
					(BigInteger) ((Object[]) objeto)[4]));
		}
		return results;
	}
}
