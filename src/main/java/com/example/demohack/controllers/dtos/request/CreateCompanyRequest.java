package com.example.demohack.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCompanyRequest {

    private String name;
    private String description;
    private String address;
    private String register_date;
}
