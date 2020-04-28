package co.com.claro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.claro.entity.CustomParameter;
import co.com.claro.repo.CustomParameterRepository;

@Service
@Transactional
public class CustomParameterService {
	
	private final CustomParameterRepository customParameterRepository;

    @Autowired
    public CustomParameterService(CustomParameterRepository customParameterRepository){
        this.customParameterRepository = customParameterRepository;
    }
    
    public List<CustomParameter> getAllParameters() {
        return this.customParameterRepository.findAll();
    }
    
    public Optional<CustomParameter> getCustomParameterById(Long id) {
    	return this.customParameterRepository.findById(id);
    }
    
    public CustomParameter createCustomParameter(CustomParameter parameter) {
    	return this.customParameterRepository.save(parameter);
    }
    
    public CustomParameter updateCustomParameter(CustomParameter parameter) {
    	return this.customParameterRepository.save(parameter);
    }
    
    public void deleteCustomParameter(CustomParameter parameter) {
    	 this.customParameterRepository.delete(parameter);
    }
    
}
