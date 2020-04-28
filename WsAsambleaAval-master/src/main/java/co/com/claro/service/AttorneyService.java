package co.com.claro.service;

import co.com.claro.entity.Attorney;
import co.com.claro.repo.AttorneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttorneyService {
    private final AttorneyRepository attorneyRepository;

    @Autowired
    public AttorneyService(AttorneyRepository attorneyRepository) {
        this.attorneyRepository = attorneyRepository;
    }

    public List<Attorney> getAllAttorney(){
        return this.attorneyRepository.findAll();
    }

    public Attorney createAttorney(Attorney attorney){
        return this.attorneyRepository.save(attorney);
    }

    public Attorney updateAttorney(Attorney attorney){
        return this.attorneyRepository.save(attorney);
    }

    public Optional<Attorney> getAttorneyByid(String tip, Long id){
        return this.attorneyRepository.findByTipIdAndNumId(tip,id);
    }
}
