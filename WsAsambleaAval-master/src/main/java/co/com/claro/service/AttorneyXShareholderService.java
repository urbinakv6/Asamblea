package co.com.claro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.claro.entity.AttorneyXShareHolder;
import co.com.claro.repo.AttorneyXShareholdeRepository;

@Service
@Transactional
public class AttorneyXShareholderService {
    private final AttorneyXShareholdeRepository attorneyXShareholdeRepository;

    @Autowired
    public AttorneyXShareholderService(AttorneyXShareholdeRepository attorneyXShareholdeRepository) {
        this.attorneyXShareholdeRepository = attorneyXShareholdeRepository;
    }

    public List<AttorneyXShareHolder> getAllAttorneyXShareHolder(){
        return this.attorneyXShareholdeRepository.findAll();
    }

    public Optional<AttorneyXShareHolder> getAttorneyXShareHolderById(Long id){
        return this.attorneyXShareholdeRepository.findById(id);
    }

    public AttorneyXShareHolder createAttorneyXShareHolder(AttorneyXShareHolder xShareHolder){
        return this.attorneyXShareholdeRepository.save(xShareHolder);
    }

    public AttorneyXShareHolder updateAttorneyXShareHolder(AttorneyXShareHolder xShareHolder){
        return this.attorneyXShareholdeRepository.save(xShareHolder);
    }

    public List<String> getActionsByAttorney(String tipId, String numID){
        return this.attorneyXShareholdeRepository.actionsByDoc(tipId, numID);
    }
    
    public void uploadAttorneyXShareHolder(List<AttorneyXShareHolder> xShareHolder) {
    	attorneyXShareholdeRepository.saveAll(xShareHolder);
    }
}
