package com.smartiq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class WrongUsernamePasswordException extends Exception {

    public WrongUsernamePasswordException() {
        super();
    }

    public WrongUsernamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUsernamePasswordException(String message) {
        super(message);
    }

    public WrongUsernamePasswordException(Throwable cause) {
        super(cause);
    }

}
