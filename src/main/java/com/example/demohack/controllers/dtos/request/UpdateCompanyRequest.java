package com.example.demohack.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCompanyRequest {

    private String name;
    private String description;
    private String address;
    private String register_date;
    private String photo;
}
