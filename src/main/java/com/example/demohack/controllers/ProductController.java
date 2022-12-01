package com.example.demohack.controllers;

import com.example.demohack.controllers.dtos.request.CreateProductRequest;
import com.example.demohack.controllers.dtos.request.UpdateProductRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> get (@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    ResponseEntity<BaseResponse> getAll ( ) {
        BaseResponse baseResponse = service.getAll();
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreateProductRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable @Valid Long id, @RequestBody UpdateProductRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("company/{id}")
    ResponseEntity<BaseResponse> getAllProductsByCompanyId (@PathVariable Long id) {
        BaseResponse baseResponse = service.getAllProductsByCompanyId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
