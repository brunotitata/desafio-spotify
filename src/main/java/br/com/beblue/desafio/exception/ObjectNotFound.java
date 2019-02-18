package br.com.beblue.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFound extends RuntimeException {

    private static final long serialVersionUID = -3155492234853633406L;

    public ObjectNotFound(String msg) {
        super(msg);
    }

}
