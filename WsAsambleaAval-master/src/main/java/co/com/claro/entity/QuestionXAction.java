package co.com.claro.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Getter @Setter
    private Long id;

    @Id
    @Column(name = "ID_PREGUNTA", length = 15)
    @Getter @Setter
    private Long idPregunta;

    @Id
    @Column(name = "NUMERO_ACCION", length = 20)
    @Getter @Setter
    private String numeroAccion;

    @Column(name = "RESPUESTA", length = 20)
    @Getter @Setter
    private String respuesta;

    @Column(name = "OBSERVACION", length = 80)
    @Getter @Setter
    private String observacion;

    @Column(name = "FEC_RESPUESTA")
    @Getter @Setter
    private Date fecRespuesta;

    @Column(name = "IP_RESPUESTA", length = 20)
    @Getter @Setter
    private String ipRespuesta;

}
