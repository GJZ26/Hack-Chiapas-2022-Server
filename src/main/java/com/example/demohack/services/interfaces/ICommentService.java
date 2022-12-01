package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateCommentRequest;
import com.example.demohack.controllers.dtos.request.UpdateCommentRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;

public interface ICommentService {
    BaseResponse get(Long id);
    
    BaseResponse list();
    
    BaseResponse create(CreateCommentRequest request);
    
    void delete(Long id);
    
    BaseResponse update(Long id, UpdateCommentRequest request);

    BaseResponse listAllCommentsByUserId(Long userId);
    
    BaseResponse listAllCommentsByPublicationId(Long publicationId);
}
