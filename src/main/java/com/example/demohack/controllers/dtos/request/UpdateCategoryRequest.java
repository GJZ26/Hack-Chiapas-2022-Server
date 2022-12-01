package com.example.demohack.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCategoryRequest {
    private String name;
    private String description;
}
