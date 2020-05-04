package co.com.claro.web.utils;

import java.util.ArrayList;
import java.util.List;

import co.com.claro.entity.QuestionXResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionXResultResponse {

	private List<QuestionXResult> results = new ArrayList<>();
	private GenericResponse responseStatus;
}
