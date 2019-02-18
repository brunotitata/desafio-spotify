package br.com.beblue.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = -743041796350244368L;

    public IllegalArgumentException(String msg) {
        super(msg);
    }

}
