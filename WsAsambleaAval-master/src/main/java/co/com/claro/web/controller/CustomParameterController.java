package co.com.claro.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.claro.entity.CustomParameter;
import co.com.claro.service.CustomParameterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/CustomParameter")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class CustomParameterController {
	
private final CustomParameterService customParameterService;
    
    @Autowired
    public CustomParameterController(CustomParameterService customParameterService) {
        this.customParameterService = customParameterService;
    }

    @GetMapping("")
    public List<CustomParameter> getCustomParameter() {
        log.info("process=get-CustomParameter");
        return customParameterService.getAllParameters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomParameter> getCustomParameterById( @PathVariable Long id) {
        log.info("process=get-CustomParameter", id);
        Optional<CustomParameter> CustomParameter = customParameterService.getCustomParameterById(id);
        return CustomParameter.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public CustomParameter createCustomParameter(@RequestBody CustomParameter parameter) {
        log.info("process=create-CustomParameter", parameter.getParameter());
        return this.customParameterService.createCustomParameter(parameter);
    }

    @PutMapping("/{id}")
    public CustomParameter updateCustomParameter(@PathVariable Long id,
                                            @RequestBody CustomParameter parameter) {
        log.info("process=update-CustomParameter", parameter.getParameter());
        parameter.setId(id);
        return this.customParameterService.updateCustomParameter(parameter);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        return customParameterService.getCustomParameterById(id)
                .map(parameter -> {
                	customParameterService.deleteCustomParameter(parameter);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
}
