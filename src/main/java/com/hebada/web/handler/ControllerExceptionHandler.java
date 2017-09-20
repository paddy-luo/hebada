package com.hebada.web.handler;

import com.hebada.web.exception.NoAuthorizedExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> handleUnAuthorizedException() {
        return new ResponseEntity<String>("操作失败，请联系管理员！", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {
        LOGGER.error("ex: {}", ex);
        return new ResponseEntity<String>("操作失败，请联系管理员！", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
