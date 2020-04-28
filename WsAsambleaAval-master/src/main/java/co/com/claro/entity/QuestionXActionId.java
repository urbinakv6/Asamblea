package co.com.claro.entity;

import java.io.Serializable;

public class QuestionXActionId implements Serializable {

    private Long id;
    private Long idPregunta;
    private String numeroAccion;

    public QuestionXActionId() {
    }

    public QuestionXActionId(Long id, Long idPregunta, String numeroAccion) {
        this.id = id;
        this.idPregunta = idPregunta;
        this.numeroAccion = numeroAccion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id ==null) ?0 : id.hashCode() );
        result = prime * result + ((idPregunta ==null) ?0 : idPregunta.hashCode() );
        result = prime * result + ((numeroAccion ==null) ?0 : numeroAccion.hashCode() );
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuestionXActionId other = (QuestionXActionId) obj;
        if (numeroAccion == null){
            if (other.numeroAccion != null)
                return false;
        }else if (!numeroAccion.equals(other.numeroAccion))
            return false;
        return true;
    }
}
