package co.com.claro.web.controller;

import co.com.claro.entity.Attorney;
import co.com.claro.entity.AttorneyXShareHolder;
import co.com.claro.entity.ShareHolder;
import co.com.claro.service.AttorneyService;
import co.com.claro.service.AttorneyXShareholderService;
import co.com.claro.service.ShareHolderService;
import co.com.claro.web.utils.AttorneyCreate;
import co.com.claro.web.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Attorney")
@Slf4j
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.OPTIONS,
        RequestMethod.PUT })
public class AttorneyController {

    private final AttorneyService attorneyService;
    private final ShareHolderService shareHolderService;
    private final AttorneyXShareholderService xShareholderService;

    @Autowired
    public AttorneyController(AttorneyService attorneyService, ShareHolderService shareHolderService,
                              AttorneyXShareholderService xShareholderService) {
        this.attorneyService = attorneyService;
        this.shareHolderService = shareHolderService;
        this.xShareholderService = xShareholderService;
    }

    @GetMapping("")
    public List<Attorney> getAttorney() {
        log.info("process=get-Attorney");
        return attorneyService.getAllAttorney();
    }

    @GetMapping("/{tip}/{num}")
    public ResponseEntity<Attorney> getAttorneyById( @PathVariable String tip ,
                                                    @PathVariable Long num) {
        log.info("process=get-attorney, Attorney_num={}", num);
        Optional<Attorney> attorney = attorneyService.getAttorneyByid(tip,num);
        return attorney.map( u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public GenericResponse createAttorney(@RequestBody AttorneyCreate attorney) {
        log.info("process=create-Attorney, Attorney_correo={}", attorney.getAttorney().getCorreo());
        GenericResponse response = new GenericResponse("00","Ok","Ok");
        Optional<ShareHolder> shareHolder  ;
        Attorney created = new Attorney();
        Optional<ShareHolder> isAttorney;
        AttorneyXShareHolder relacion = new AttorneyXShareHolder();
        try {
            shareHolder = this.shareHolderService.getShareHolderById(attorney.getAccTipId(),
                    attorney.getAccNumId(), attorney.getAccNumAccion());
            //isAttorney = this.shareHolderService.getShareHolderByDoc(attorney.getAttorney().getTipId(),
             //       attorney.getAttorney().getNumId());
            if (shareHolder.isPresent()  ){
                ShareHolder sh =  shareHolder.get();
               // ShareHolder att = isAttorney.get();
                //boolean isSame = sh.getTipId().equals(att.getTipId()) && sh.getNumId().equals(att.getNumId());
                if (true){
                    created = this.attorneyService.createAttorney(attorney.getAttorney());
                    relacion.setApoNumId(created.getNumId());
                    relacion.setApoTipId(created.getTipId());
                    relacion.setAccTipId(shareHolder.get().getTipId());
                    relacion.setAccNumId(shareHolder.get().getNumId());
                    relacion.setAccNumAccion(shareHolder.get().getNumeroAccion());
                    relacion.setIpUltAcceso(attorney.getIpAcces());
                    relacion.setFecCrea(new Date());
                    relacion.setFecUltAcceso(new Date());
                    AttorneyXShareHolder asociate=  this.xShareholderService.createAttorneyXShareHolder(relacion);
                    if (asociate.getId() == null){
                        response.setReturnCode("10");
                        response.setDescriptoCode("operacion no pudo realizarce");
                        response.setMenssage("Contacte a su administrador");
                    }
                    sh.setAutoriza(false);
                    sh.setIpAcceso(attorney.getIpAcces());
                    sh.setFechaUltimoAcceso(new Date());
                    this.shareHolderService.updateShareHolder(sh);
                    /*if (!att.isApoderado()){
                        att.setApoderado(true);
                        this.shareHolderService.updateShareHolder(att);
                    }*/
                }
            }else {
                response.setReturnCode("10");
                response.setDescriptoCode("operacion no pudo realizarce");
                response.setMenssage("accionista o apoderado no existen");
            }
        }catch (Exception e){
            response.setReturnCode("99");
            response.setDescriptoCode(e.getMessage());
            response.setMenssage("operación no pudo ser realizada");
        }
        return response ;
    }

    @PutMapping("/{tip}/{num}")
    public Attorney updateAttorney(  @PathVariable String tip , 
                                     @PathVariable Long num,
                                     @RequestBody Attorney attorney) {
        log.info("process=updatete-attorney, Attorney_correo={}", attorney.getCorreo());
        attorney.setTipId(tip);
        attorney.setNumId(num.toString());
        return this.attorneyService.updateAttorney(attorney);
    }
}
