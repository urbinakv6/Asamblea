package co.com.claro.web.utils;

import co.com.claro.entity.Attorney;
import lombok.Getter;
import lombok.Setter;

public class AttorneyCreate {

    @Getter @Setter
    private Attorney attorney;

    @Getter @Setter
    private String accTipId;

    @Getter @Setter
    private String accNumId;

    @Getter @Setter
    private String accNumAccion;

    @Getter @Setter
    private String ipAcces;

    public AttorneyCreate() {
    }

    public AttorneyCreate(Attorney attorney, String accTipId, String accNumId, String accNumAccion, String ipAcces) {
        this.attorney = attorney;
        this.accTipId = accTipId;
        this.accNumId = accNumId;
        this.accNumAccion = accNumAccion;
        this.ipAcces = ipAcces;
    }
}
