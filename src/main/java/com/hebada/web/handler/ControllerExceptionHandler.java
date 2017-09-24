package com.hebada.web.handler;

import com.hebada.web.exception.NoAuthorizedExeption;
import com.hebada.web.response.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by paddy.luo on 2017/9/19.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = NoAuthorizedExeption.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AjaxResponse> handleUnAuthorizedException() {
        return new ResponseEntity<AjaxResponse>(AjaxResponse.error().withDescription("Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<AjaxResponse> handleException(Exception ex) {
        LOGGER.error("ex: {}", ex);
        return new ResponseEntity<AjaxResponse>(AjaxResponse.error().withDescription("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<AjaxResponse> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.warn("ex: {}", ex);
        return new ResponseEntity<AjaxResponse>(AjaxResponse.error().withDescription(
            ex.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
