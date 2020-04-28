package co.com.claro.service;

import co.com.claro.entity.QuestionXAction;
import co.com.claro.repo.QuestionXActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionXActionService {
    private final QuestionXActionRepository xActionRepository;

    @Autowired
    public QuestionXActionService(QuestionXActionRepository xActionRepository) {
        this.xActionRepository = xActionRepository;
    }

    public List<QuestionXAction> getAllQuestionXAction(){
        return this.xActionRepository.findAll();
    }

    public Optional<QuestionXAction> getQuestionXActionById(Long id){
        return this.xActionRepository.findById(id);
    }

    public QuestionXAction createQuestionXAction(QuestionXAction xAction){
        return this.xActionRepository.save(xAction);
    }

    public QuestionXAction updateQuestionXAction(QuestionXAction xAction){
        return this.xActionRepository.save(xAction);
    }
}
