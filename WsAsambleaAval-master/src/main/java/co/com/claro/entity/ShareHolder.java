package co.com.claro.entity;

import lombok.*;

import javax.persistence.*;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACCIONISTAS")
@IdClass(ShareHolderId.class)
public class ShareHolder {

	@CsvBindByName(column = "TIP_ID")
	@Id
    @Column(name = "TIP_ID", length = 3)
    @Getter @Setter
    private String tipId;

	@CsvBindByName(column = "NUM_ID")
	@Id
    @Column(name = "NUM_ID", length = 20)
    @Getter @Setter
    private String numId;

	@CsvBindByName(column = "CTA. INVERSIONISTA")
	@Id
    @Column(name = "NUMERO_ACCION", length = 20)
    @Getter @Setter
    private String numeroAccion;

	@CsvBindByName(column = "NOMBRE INVERSIONISTA")
	@Column(name = "NOMBRES_APELLIDOS", length = 100)
    @Getter @Setter
    private String nombresApellidos;

	@CsvBindByName(column = "CANT. ACCIONES")
	@Column(name = "CORREO", length = 100)
    @Getter @Setter
    private String correo;

	@CsvBindByName(column = "APODERADO")
	@Column(name = "APODERADO")
    @Getter @Setter
    private boolean apoderado;

	@CsvBindByName(column = "PARTICIPACION (%)")
	@Column(name = "SALDO_TOTAL", length = 15, scale = 3)
    @Getter @Setter
    private Double saldoTotal;

	@CsvBindByName(column = "IP_ACCESO")
	@Column(name = "IP_ACCESO", length = 20)
    @Getter @Setter
    private String ipAcceso;

	@CsvBindByName(column = "FECHA_ULTIMO_ACCESO")
	@CsvDate(value = "dd/MM/yyyy HH:mm")
	@Column(name = "FECHA_ULTIMO_ACCESO")
    @Getter @Setter
    private Date fechaUltimoAcceso;

	@CsvBindByName(column = "FECHA_CREACION")
	@CsvDate(value = "dd/MM/yyyy HH:mm")
	@Column(name = "FECHA_CREACION")
    @Getter @Setter
    private Date fechaCreacion;

	@CsvBindByName(column = "VOTACION")
	@Column(name = "AUTORIZA")
    @Getter @Setter
    private boolean autoriza;

	@CsvBindByName(column = "MODERADOR")
	@Column(name = "MODERADOR")
    @Getter @Setter
    private boolean moderador;

	@CsvBindByName(column = "PREGUNTAS ASIGNADAS")
	@Column(name = "PREGUNTAS")
    @Getter @Setter
    private String Preguntas;

}
