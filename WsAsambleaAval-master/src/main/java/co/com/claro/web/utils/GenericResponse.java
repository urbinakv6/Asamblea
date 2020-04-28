package co.com.claro.web.utils;

import lombok.Getter;
import lombok.Setter;

public class GenericResponse {

    @Getter @Setter
    private String returnCode;

    @Getter @Setter
    private String descriptoCode;

    @Getter @Setter
    private String menssage;

    public GenericResponse() {
    }

    public GenericResponse(String returnCode, String descriptoCode, String menssage) {
        this.returnCode = returnCode;
        this.descriptoCode = descriptoCode;
        this.menssage = menssage;
    }
}
