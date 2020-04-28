package co.com.claro.web.utils;

import co.com.claro.entity.ShareHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ShareholderResponse {

    @Getter @Setter
    private ShareHolder shareHolder;

    @Getter @Setter
    private List<String> actionsAttorney;

    @Getter @Setter
    private GenericResponse responseStatus;

    public ShareholderResponse() {
    }

    public ShareholderResponse(ShareHolder shareHolder, GenericResponse responseStatus, List<String> actionsAttorney) {
        this.shareHolder = shareHolder;
        this.responseStatus = responseStatus;
        this.actionsAttorney = actionsAttorney;
    }
}
