package com.example.demohack.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class CommentResponse {
    private Long id;
        
    private String email;
    
    private String comment;
    
    private String date;
    
    private String status;
    
    private CreateUserResponse user;

    private CreatePublicationResponse publication;
    
}