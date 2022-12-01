package com.example.demohack.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserResponse {
    private Long id;
    private String username;
    private String email;
}
