package com.example.coffeebe.domain.utils.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    HttpStatus status;

    public CustomException(HttpStatus status, CustomErrorMessage msg) {
        super(msg.val);
        this.status = status;
    }

    public CustomException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }
}
