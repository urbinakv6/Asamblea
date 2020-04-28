package co.com.claro.entity;

import lombok.*;

import javax.persistence.*;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "APODERADO_X_ACCIONISTAS")
public class AttorneyXShareHolder {
    @Id
    @SequenceGenerator(name = "AttorneyXShareHolder_id_generator",
            sequenceName = "AttorneyXShareHolder_id_seq")
    @GeneratedValue(generator = "AttorneyXShareHolder_id_generator")
    @Getter @Setter
    private Long id;

    @CsvBindByName(column = "TIP_ID APODERADO")
    @Column(name = "APO_TIP_ID", length = 3)
    @Getter @Setter
    private String  apoTipId;

    @CsvBindByName(column = "NUM_ID APODERADO")
    @Column(name = "APO_NUM_ID", length = 20)
    @Getter @Setter
    private String apoNumId;

    @CsvBindByName(column = "TIP_ID ACCIONISTA")
    @Column(name = "ACC_TIP_ID", length = 3)
    @Getter @Setter
    private String accTipId;

    @CsvBindByName(column = "NUM_ID ACCIONISTA")
    @Column(name = "ACC_NUM_ID", length = 20)
    @Getter @Setter
    private String accNumId;

    @CsvBindByName(column = "CTA. INVERSIONISTA")
    @Column(name = "ACC_NUM_ACCION", length = 20)
    @Getter @Setter
    private String accNumAccion;

    @CsvBindByName(column = "FECHA_CREACION")
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    @Column(name = "FEC_CREA")
    @Getter @Setter
    private Date fecCrea;

    @CsvBindByName(column = "FEC_ULT_ACCESO")
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    @Column(name = "FEC_ULT_ACCESO")
    @Getter @Setter
    private Date fecUltAcceso;

    @CsvBindByName(column = "IP_ULT_ACCESO")
    @Column(name = "IP_ULT_ACCESO", length = 20)
    @Getter @Setter
    private String ipUltAcceso;

}
