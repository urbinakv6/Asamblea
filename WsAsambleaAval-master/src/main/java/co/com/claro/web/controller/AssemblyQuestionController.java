package co.com.claro.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import co.com.claro.entity.AssemblyQuestions;
import co.com.claro.service.AssemblyQuestionService;
import co.com.claro.web.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/AssemblyQuestion")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class AssemblyQuestionController {
    private final AssemblyQuestionService assemblyQuestionService;
    
    @Autowired
    public AssemblyQuestionController(AssemblyQuestionService assemblyQuestionService) {
        this.assemblyQuestionService = assemblyQuestionService;
    }

    @GetMapping("")
    public List<AssemblyQuestions> getAssemblyQuestions() {
        log.info("process=get-AssemblyQuestions");
        return assemblyQuestionService.getAllAssemblyQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssemblyQuestions> getAssemblyQuestionById( @PathVariable Long id) {
        log.info("process=get-AssemblyQuestions, AssemblyQuestions_num={}", id);
        Optional<AssemblyQuestions> assemblyQuestions = assemblyQuestionService.getAssemblyQuestionsById(id);
        return assemblyQuestions.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public AssemblyQuestions createAssemblyQuestion(@RequestBody AssemblyQuestions questions) {
        log.info("process=create-AssemblyQuestions, AssemblyQuestions_correo={}", questions.getPregunta());
        return this.assemblyQuestionService.createAssemblyQuestions(questions);
    }

    @PutMapping("/{id}")
    public AssemblyQuestions updateAssemblyQuestion(@PathVariable Long id,
                                            @RequestBody AssemblyQuestions questions) {
        log.info("process=updatete-AssemblyQuestions, AssemblyQuestions_correo={}", questions.getPregunta());
        questions.setId(id);
        return this.assemblyQuestionService.updateAssemblyQuestions(questions);
    }
    
    @PostMapping("/upload-csv-file")
    public GenericResponse uploadCSVFile(@RequestParam("file") MultipartFile file) {
    	GenericResponse response = null;
        if (file == null || file.isEmpty()) {
        	return new GenericResponse("400", "Invalid request", "Please select a CSV file to upload");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "ISO-8859-1"))) {
				CsvToBean<AssemblyQuestions> csvToBean = new CsvToBeanBuilder<AssemblyQuestions>(reader)
                        .withType(AssemblyQuestions.class).withIgnoreLeadingWhiteSpace(true).build();
                List<AssemblyQuestions> questions = csvToBean.parse();
                assemblyQuestionService.deleteAssemblyQuestions();
                assemblyQuestionService.uploadAssemblyQuestions(questions);
                response = new GenericResponse("200", "Ok", "CSV file loaded successfully");
            } catch (Exception ex) {
            	ex.printStackTrace();
            	response = new GenericResponse("500", "Error", "An error occurred while processing the CSV file");
            }
        }
        return response;
    }
}
    
