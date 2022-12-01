package com.example.demohack.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentRequest {

    private String email;

    private String comment; 

    private Long userId;

    private Long publicationId;
    
}
