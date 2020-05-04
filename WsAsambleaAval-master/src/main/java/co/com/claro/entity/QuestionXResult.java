package co.com.claro.entity;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionXResult {

	private BigInteger idPregunta;
	private String descPregunta;
	private BigInteger aprobado;
	private BigInteger noAprobado;
	private BigInteger meAbstengo;

}
