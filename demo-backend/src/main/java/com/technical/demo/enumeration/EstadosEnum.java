package com.technical.demo.enumeration;

import com.technical.demo.beans.response.Estados;
import com.technical.demo.beans.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum EstadosEnum {

    /**
     * Errores de logica.
     */
    STATUS_SUCCESSFULL("0000", "El servicio se ha ejecutado de forma correcta."),
    BAD_FORMAT("0001", "Valide los par\u00E1metros."),
    STATUS_FAIL("0003", "Ocurri\u00F3 un error en el proceso."),

    /**
     * Errores de store.
     */
    EMAIL_USED("1001", "El correo ya ha sido usado."),
    ;

    private final String code;
    private final String message;

    EstadosEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Estados create() {
        return new Estados(this.code, this.message);
    }

    public boolean isCode(String code) {
        return this.code.equals(code);
    }

    public boolean isCode(Estados status) {
        return isCode(status.getCode());
    }

    public <T> ResponseEntity<Response<T>> internalError(Class<T> clazz) {
        Response<T> response = new Response<>();
        response.setStatus(this.create());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
