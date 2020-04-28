package co.com.claro.entity;

import java.io.Serializable;

public class ShareHolderId implements Serializable {
    private static final long serialVersionUID =1L;

    private String tipId;
    private String numId;
    private String numeroAccion;

    public ShareHolderId() {
    }

    public ShareHolderId(String tipId, String numId, String numeroAccion) {
        this.tipId = tipId;
        this.numId = numId;
        this.numeroAccion = numeroAccion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipId ==null) ?0 : tipId.hashCode() );
        result = prime * result + ((numId ==null) ?0 : numId.hashCode() );
        result = prime * result + ((numeroAccion ==null) ? 0 : numeroAccion.hashCode() );
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
        ShareHolderId other = (ShareHolderId) obj;
        if (tipId == null){
            if (other.tipId != null)
                return false;
        }else if (!tipId.equals(other.tipId))
            return false;
        if (numeroAccion == null){
            if (other.numeroAccion != null)
                return false;
        }else if (!numeroAccion.equals(other.numeroAccion))
            return false;
        return true;
    }
}
