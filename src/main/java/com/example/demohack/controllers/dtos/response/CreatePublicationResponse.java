package com.example.demohack.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePublicationResponse {
    private Long id;
    private String creationDate;
    private String image;
}
