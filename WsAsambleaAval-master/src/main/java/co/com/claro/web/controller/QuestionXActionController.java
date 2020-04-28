package co.com.claro.web.controller;


import co.com.claro.entity.QuestionXAction;
import co.com.claro.service.QuestionXActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/QuestionXAction")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class QuestionXActionController {

    private final QuestionXActionService xActionService;

    @Autowired
    public QuestionXActionController(QuestionXActionService xActionService) {
        this.xActionService = xActionService;
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
