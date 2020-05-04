package co.com.claro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.claro.entity.QuestionXResult;
import co.com.claro.repo.QuestionXResultRepository;
import co.com.claro.web.utils.GenericResponse;
import co.com.claro.web.utils.QuestionXResultResponse;

@Service
public class QuestionXResultService {

	private final QuestionXResultRepository xResultRepo;
	
    @Autowired
    public QuestionXResultService(QuestionXResultRepository xResultRepo) {
        this.xResultRepo = xResultRepo;
    }
	
	public QuestionXResultResponse findResults() {
		QuestionXResultResponse results = new QuestionXResultResponse();
		List<QuestionXResult> listResults = new ArrayList<>();
		
		try {
			listResults = xResultRepo.findAllResults();
			if(listResults != null && !listResults.isEmpty() && listResults.size() > 0) {
				results.setResults(listResults);
				results.setResponseStatus(new GenericResponse("200", "Ok", "Successfully, results information found"));
			} else {
				results.setResponseStatus(new GenericResponse("100", "Ok", "No results information"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			results.setResponseStatus(new GenericResponse("500", "Error", "An error has occurred in the results query"));
		}
		return results;
	}

}
