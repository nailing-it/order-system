package com.cheolgabang.codingtest.common;

import org.springframework.http.HttpStatus;

public class NotImplementedYetException extends BusinessException {

    public NotImplementedYetException(String message) {
        super("NOT_IMPLEMENTED_YET", message, HttpStatus.NOT_IMPLEMENTED);
    }
}
