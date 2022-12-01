package com.example.demohack.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String register_date;
    private String photo;
    private GetCategoryResponse category;
}
