package com.example.demohack.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProductRequest {
    private String name;
    private Double price;
    private String photo;
}
