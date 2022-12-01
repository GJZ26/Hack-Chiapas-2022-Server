package com.example.demohack.services.interfaces;



import com.example.demohack.controllers.dtos.request.CreateTagRequest;
import com.example.demohack.controllers.dtos.request.UpdateTagRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;


public interface ITagService {
    
    BaseResponse get(Long id);

    BaseResponse list();

    BaseResponse update(Long id, UpdateTagRequest request) ;

    BaseResponse create(CreateTagRequest request);

    void delete(Long id);
}
