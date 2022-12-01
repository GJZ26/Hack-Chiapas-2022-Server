package com.example.demohack.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductResponses {
    private Long id;
    private String name;
    private Double price;
    private String photo;

}
