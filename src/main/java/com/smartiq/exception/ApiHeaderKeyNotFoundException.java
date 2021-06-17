package com.smartiq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiHeaderKeyNotFoundException extends Exception {

    public ApiHeaderKeyNotFoundException() {
        super();
    }

    public ApiHeaderKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiHeaderKeyNotFoundException(String message) {
        super(message);
    }

    public ApiHeaderKeyNotFoundException(Throwable cause) {
        super(cause);
    }

}
