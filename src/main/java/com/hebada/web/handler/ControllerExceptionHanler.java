package com.hebada.web.handler;

import javax.servlet.http.HttpServletRequest;

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
public class ControllerExceptionHanler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHanler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleException(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<String>("操作失败，请联系管理员！", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
