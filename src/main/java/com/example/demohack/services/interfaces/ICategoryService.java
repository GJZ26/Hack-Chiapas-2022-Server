package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateCategoryRequest;
import com.example.demohack.controllers.dtos.request.UpdateCategoryRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;

public interface ICategoryService {
    
    BaseResponse get(Long id);

    BaseResponse list();

    BaseResponse update(Long id, UpdateCategoryRequest request);

    BaseResponse create(CreateCategoryRequest request);

    void delete(Long id);
}
