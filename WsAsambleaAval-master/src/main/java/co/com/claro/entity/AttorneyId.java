package co.com.claro.entity;

import java.io.Serializable;

public class AttorneyId implements Serializable {

    private static final long serialVersionUID =1L;

    private String tipId;
    private String numId;

    public AttorneyId() {
    }

    public AttorneyId(String tipId, String numId) {
        this.tipId = tipId;
        this.numId = numId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipId ==null) ?0 : tipId.hashCode() );
        result = prime * result + ((numId ==null) ?0 : numId.hashCode() );
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
        AttorneyId other = (AttorneyId) obj;
        if (tipId == null){
            if (other.tipId != null)
                return false;
        }else if (!tipId.equals(other.tipId))
            return false;
        return true;
    }
}
