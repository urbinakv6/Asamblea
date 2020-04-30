package co.com.claro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PREGUNTAS_ASAMBLEA")
public class AssemblyQuestions {

    @Id
    @SequenceGenerator(name = "questions_id_generator", sequenceName = "questions_id_seq")
    @GeneratedValue(generator = "questions_id_generator")
    @Getter @Setter
    private Long id;

    @CsvBindByName(column = "NO. PREGUNTA")
    @Column(name = "PREGUNTA",length = 500)
    @Getter @Setter
    private String pregunta;

    @CsvBindByName(column = "PREGUNTA")
    @Column(name = "DESC_PREGUNTA", length = 500)
    @Getter @Setter
    private String descPregunta;

    @CsvBindByName(column = "OBSERVACIONES")
    @Column(name = "OBSERVACIONES",length = 150)
    @Getter @Setter
    private String observaciones;

    @CsvBindByName(column = "ESTADO")
    @Column(name = "ESTADO", length = 1)
    @Getter @Setter
    private String estado;

    @CsvBindByName(column = "FECHA CREACION")
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    @Column(name = "FEC_CREA")
    @Getter @Setter
    private Date fehCrea;

    @CsvBindByName(column = "USUARIO CREADOR")
    @Column(name = "USER_CREA",length = 20)
    @Getter @Setter
    private String userCrea;

    @CsvBindByName(column = "FECHA MODIFICACION")
    @CsvDate(value = "dd/MM/yyyy HH:mm")
    @Column(name = "FEC_MODIFICA")
    @Getter @Setter
    private Date fehModifica;

    @CsvBindByName(column = "USUARIO MODIFICADOR")
    @Column(name = "USER_MOD", length = 20)
    @Getter @Setter
    private String userMod;
}
