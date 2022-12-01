package com.example.demohack.controllers.dtos.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class BaseRespose {
    private Object data;

    private String message;

    private Boolean success;

    private HttpStatus httpStatus;
}

