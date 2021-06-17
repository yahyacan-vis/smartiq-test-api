package com.smartiq.handler;


import com.smartiq.dto.BaseReturn;
import com.smartiq.enums.MessageTypes;
import com.smartiq.exception.ApiHeaderKeyNotFoundException;
import com.smartiq.exception.NotFoundException;
import com.smartiq.exception.WrongUsernamePasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiHeaderKeyNotFoundException.class)
    public ResponseEntity<BaseReturn> handleApiHeaderKeyNotFoundException(ApiHeaderKeyNotFoundException e, WebRequest request, Locale locale) {
        return new ResponseEntity<>(BaseReturn.builder().code(MessageTypes.API_KEY_HEADER_NOT_FOUND.getCode()).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongUsernamePasswordException.class)
    public ResponseEntity<BaseReturn> handleWrongUsernamePasswordException(WrongUsernamePasswordException e, WebRequest request, Locale locale) {
        return new ResponseEntity<>(BaseReturn.builder().code(MessageTypes.WRONG_USERNAME_PASSWORD.getCode()).message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseReturn> handleNotFoundException(NotFoundException e, WebRequest request, Locale locale) {
        return new ResponseEntity<>(BaseReturn.builder().code(MessageTypes.NOT_FOUND.getCode()).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }


}