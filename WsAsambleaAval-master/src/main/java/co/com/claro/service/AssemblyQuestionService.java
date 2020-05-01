package co.com.claro.service;

import co.com.claro.entity.AssemblyQuestions;
import co.com.claro.repo.AssemblyQuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssemblyQuestionService {

    private final AssemblyQuestionRepository assemblyQuestionRepository;

    @Autowired
    public AssemblyQuestionService(AssemblyQuestionRepository assemblyQuestionRepository) {
        this.assemblyQuestionRepository = assemblyQuestionRepository;
    }

    public List<AssemblyQuestions> getAllAssemblyQuestions(){
        //return this.assemblyQuestionRepository.findAll();
        return this.assemblyQuestionRepository.findAllOrderByPregunta();
    }

    public Optional<AssemblyQuestions> getAssemblyQuestionsById(Long id){
        return this.assemblyQuestionRepository.findById(id);
    }

    public AssemblyQuestions createAssemblyQuestions(AssemblyQuestions questions){
        return this.assemblyQuestionRepository.save(questions);
    }

    public AssemblyQuestions updateAssemblyQuestions(AssemblyQuestions questions){
        return this.assemblyQuestionRepository.save(questions);
    }
    
    public void uploadAssemblyQuestions(List<AssemblyQuestions> questions) {
    	this.assemblyQuestionRepository.saveAll(questions);
    }

    public void deleteAssemblyQuestions() {
    	this.assemblyQuestionRepository.deleteAll();
    }

}
