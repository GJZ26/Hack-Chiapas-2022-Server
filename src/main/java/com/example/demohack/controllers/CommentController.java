package com.example.demohack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demohack.controllers.dtos.request.CreateCommentRequest;
import com.example.demohack.controllers.dtos.request.UpdateCommentRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.services.interfaces.ICommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private ICommentService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateCommentRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse= service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<BaseResponse> listAllCommentsByIdUser(@PathVariable long id){
        BaseResponse baseResponse= service.listAllCommentsByIdUser(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable long id){
        BaseResponse baseResponse= service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateCommentRequest request){
        BaseResponse baseResponse= service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
