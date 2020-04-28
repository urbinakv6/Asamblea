package co.com.claro.web.controller;
import static org.springframework.http.HttpStatus.CREATED;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.com.claro.entity.ShareHolder;
import co.com.claro.service.AttorneyXShareholderService;
import co.com.claro.service.ShareHolderService;
import co.com.claro.web.utils.GenericResponse;
import co.com.claro.web.utils.ShareholderResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/shareHolder")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class ShareHolderController {
    private final ShareHolderService shareHolderService;
    private final AttorneyXShareholderService xShareholderService;
    GenericResponse response = new GenericResponse("00","Ok","Ok");

    @Autowired
    public ShareHolderController(ShareHolderService shareHolderService,
                                 AttorneyXShareholderService xShareholderService){
        this.shareHolderService = shareHolderService;
        this.xShareholderService = xShareholderService;
    }

    @GetMapping("")
    public List<ShareHolder> getShareHolders() {
        log.info("process=get-shareHolders");
        return shareHolderService.getAllShareHolder();
    }

    @GetMapping("/{tip}/{num}/{accion}/{ip}")
    public ShareholderResponse getShareHolderById(@PathVariable String tip ,
                                                  @PathVariable String num,
                                                  @PathVariable String accion,
                                                  @PathVariable String ip) {
        log.info("process=get-shareHolder, shareHolder_num={}", num);
        ShareholderResponse response = new ShareholderResponse();
        List<String> actionsAttorney = new ArrayList<>();
        GenericResponse generic = new GenericResponse("00","Ok","Ok");
        ShareHolder sh = new ShareHolder();
        try{
            Optional<ShareHolder> shareHolder = shareHolderService.getShareHolderById(tip,num,accion);
            if (shareHolder.isPresent()){
                sh = shareHolder.get();
                List<String> autoriza = new ArrayList<>();
                //actionsAttorney = this.shareHolderService.getActions(tip,num);
                boolean acceso = true;
                acceso = (sh.getIpAcceso() == null || sh.getIpAcceso().equals(""));
                if (!acceso){
                    String [] au = sh.getIpAcceso().split(";");
                    String[] ipState = ip.split(";");
                    autoriza.addAll(Arrays.asList(au)) ;
                    acceso = !autoriza.get(autoriza.size() -1).equals(ipState[ipState.length -1]);
                }
                if (acceso ) {
                    sh.setIpAcceso(ip);
                    shareHolderService.updateShareHolder(sh);
                    actionsAttorney = this.xShareholderService.getActionsByAttorney(tip, num);

                }else {
                    generic.setReturnCode("98");
                    generic.setDescriptoCode("denied access");
                    generic.setMenssage("el usuario se encuentra logueado en otro equipo");
                }
            }else {
                generic.setReturnCode("10");
                generic.setDescriptoCode("NOT FOUND");
                generic.setMenssage("la identifiación ó Número de acción no corresponde");
            }
        }catch (Exception e){
            generic.setReturnCode("99");
            generic.setDescriptoCode("SYSTEM ERROR");
            generic.setMenssage("Contacte con el Administrador");
        }
        response.setShareHolder(sh);
        response.setActionsAttorney(actionsAttorney);
        response.setResponseStatus(generic);
        return response;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public ShareHolder createShareHolder(@RequestBody ShareHolder shareHolder) {
        log.info("process=create-shareHolder, shareHolder_correo={}", shareHolder.getCorreo());
        return this.shareHolderService.createShareHolder(shareHolder);
    }

    @PutMapping("/{tip}/{num}/{accion}")
    public ShareHolder updateShareHolder(  @PathVariable String tip ,
                                           @PathVariable Long num,
                                           @PathVariable String accion,
                                           @RequestBody ShareHolder shareHolder) {
        log.info("process=updatete-shareHolder, shareHolder_correo={}", shareHolder.getCorreo());
        shareHolder.setTipId(tip);
        shareHolder.setNumId(num.toString());
        shareHolder.setNumeroAccion(accion);
        return this.shareHolderService.updateShareHolder(shareHolder);
    }
    
    @PostMapping("/upload-csv-file")
    public GenericResponse uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
        	response.setReturnCode("400");
        	response.setDescriptoCode("Invalid request");
        	response.setMenssage("Please select a CSV file to upload");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "ISO-8859-1"))) {
				CsvToBean<ShareHolder> csvToBean = new CsvToBeanBuilder<ShareHolder>(reader)
                        .withType(ShareHolder.class).withIgnoreLeadingWhiteSpace(true).build();
                List<ShareHolder> shareHolder = csvToBean.parse();
                shareHolderService.uploadShareHolders(shareHolder);
            } catch (Exception ex) {
                response.setReturnCode("500");
                response.setDescriptoCode("Error");
                response.setMenssage("An error occurred while processing the CSV file");
            }
        }
        response.setReturnCode("200");
        response.setDescriptoCode("Ok");
        response.setMenssage("CSV file loaded successfully");
        return response;
    }

}
