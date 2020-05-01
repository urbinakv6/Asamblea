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

import co.com.claro.entity.AttorneyXShareHolder;
import co.com.claro.service.AttorneyXShareholderService;
import co.com.claro.web.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/AttorneyXShareHolder")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class AttorneyXShareHolderController {
    private final AttorneyXShareholderService xShareholderService;

    @Autowired
    public AttorneyXShareHolderController(AttorneyXShareholderService xShareholderService) {
        this.xShareholderService = xShareholderService;
    }

    @GetMapping("") 
    public List<AttorneyXShareHolder> getAllAttorneyXShareHolder() {
        log.info("process=get-AttorneyXShareHolder");
        return xShareholderService.getAllAttorneyXShareHolder();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttorneyXShareHolder> getAttorneyXShareHolderById( @PathVariable Long id) {
        log.info("process=get-AttorneyXShareHolder, AttorneyXShareHolder_id={}", id);
        Optional<AttorneyXShareHolder> attorneyXShareHolder = xShareholderService.getAttorneyXShareHolderById(id);
        return attorneyXShareHolder.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public AttorneyXShareHolder createAttorneyXShareHolder(@RequestBody AttorneyXShareHolder attorneyXShareHolder) {
        log.info("process=create-AttorneyXShareHolder, AttorneyXShareHolder_correo={}", attorneyXShareHolder.getApoNumId());
        return this.xShareholderService.createAttorneyXShareHolder(attorneyXShareHolder);
    }

    @PutMapping("/{id}")
    public AttorneyXShareHolder updateAttorneyXShareHolder(@PathVariable Long id,
                                            @RequestBody AttorneyXShareHolder attorneyXShareHolder) {
        log.info("process=updatete-AttorneyXShareHolder, AttorneyXShareHolder_correo={}",
                attorneyXShareHolder.getIpUltAcceso());
        attorneyXShareHolder.setId(id);
        return this.xShareholderService.updateAttorneyXShareHolder(attorneyXShareHolder);
    }
    
    @PostMapping("/upload-csv-file")
    public GenericResponse uploadCSVFile(@RequestParam("file") MultipartFile file) {
    	GenericResponse response = null;
        if (file == null || file.isEmpty()) {
        	return new GenericResponse("400", "Invalid request", "Please select a CSV file to upload");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "ISO-8859-1"))) {
				CsvToBean<AttorneyXShareHolder> csvToBean = new CsvToBeanBuilder<AttorneyXShareHolder>(reader)
                        .withType(AttorneyXShareHolder.class).withIgnoreLeadingWhiteSpace(true).build();
                List<AttorneyXShareHolder> xShareHolder = csvToBean.parse();
                xShareholderService.uploadAttorneyXShareHolder(xShareHolder);
                response = new GenericResponse("200", "Ok", "CSV file loaded successfully");
            } catch (Exception ex) {
            	ex.printStackTrace();
            	response = new GenericResponse("500", "Error", "An error occurred while processing the CSV file");
            }
        }
        return response;
    }
}
