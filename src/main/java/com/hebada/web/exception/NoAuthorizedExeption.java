package com.hebada.web.exception;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public class NoAuthorizedExeption extends RuntimeException {

    public NoAuthorizedExeption() {
    }

    public NoAuthorizedExeption(String message) {
        super(message);
    }
}
