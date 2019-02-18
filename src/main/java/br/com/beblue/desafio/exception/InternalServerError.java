package br.com.beblue.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException {
    private static final long serialVersionUID = -4549290193599225870L;

    public InternalServerError(String msg) {
        super(msg);
    }

}
