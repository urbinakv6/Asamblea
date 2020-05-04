package co.com.claro.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.claro.entity.QuestionXAction;
import co.com.claro.service.QuestionXActionService;
import co.com.claro.service.QuestionXResultService;
import co.com.claro.web.utils.QuestionXResultResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/QuestionXAction")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class QuestionXActionController {

	private final QuestionXActionService xActionService;
	private final QuestionXResultService xResultService;
	
    @Autowired
    public QuestionXActionController(QuestionXActionService xActionService, QuestionXResultService xResultService) {
		this.xActionService = xActionService;
		this.xResultService = xResultService;
    }
    
    @GetMapping("")
    public List<QuestionXAction> getAllQuestionXAction() {
        log.info("process=get-QuestionXAction");
        return xActionService.getAllQuestionXAction();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionXAction> getQuestionXActionById( @PathVariable Long id) {
        log.info("process=get-QuestionXAction, QuestionXAction_id={}", id);
        Optional<QuestionXAction> attorneyXShareHolder = xActionService.getQuestionXActionById(id);
        return attorneyXShareHolder.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    }
    
    @GetMapping("/results")
    public QuestionXResultResponse findAllQuestionByResult() {
    	log.info("process=get-QuestionXResult");
        return xResultService.findResults();
    }
    
    @PostMapping("")
    @ResponseStatus(CREATED)
    public QuestionXAction createQuestionXAction(@RequestBody QuestionXAction questionXAction) {
        log.info("process=create-QuestionXAction, QuestionXAction_correo={}", questionXAction.getRespuesta());
        return this.xActionService.createQuestionXAction(questionXAction);
    }

    @PutMapping("/{id}")
    public QuestionXAction updateQuestionXAction(@PathVariable Long id,
                                                 @RequestBody QuestionXAction questionXAction) {
        log.info("process=updatete-AttorneyXShareHolder, AttorneyXShareHolder_correo={}",
                questionXAction.getRespuesta());
        questionXAction.setId(id);
        return this.xActionService.updateQuestionXAction(questionXAction);
    }
}
