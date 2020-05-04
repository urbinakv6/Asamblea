package co.com.claro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PREGUNTA_X_ACCION")
@IdClass(QuestionXActionId.class)
public class QuestionXAction {

    @Id
    @SequenceGenerator(name = "questionxaction_id_generator", sequenceName = "questionxaction_id_seq")
    @GeneratedValue(generator = "questionxaction_id_generator")
    private Long id;

    @Id
    @Column(name = "ID_PREGUNTA", length = 15)
    private Long idPregunta;

    @Id
    @Column(name = "NUMERO_ACCION", length = 20)
    private String numeroAccion;

    @Column(name = "RESPUESTA", length = 20)
    private String respuesta;

    @Column(name = "OBSERVACION", length = 80)
    private String observacion;

    @Column(name = "FEC_RESPUESTA")
    private Date fecRespuesta;

    @Column(name = "IP_RESPUESTA", length = 20)
    private String ipRespuesta;

}
