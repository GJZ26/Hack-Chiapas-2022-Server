package com.example.demohack.controllers;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.CreatePublicationRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdatePublicationRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.services.interfaces.ICompanyService;
import com.example.demohack.services.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("publication")
public class PublicationController {
    @Autowired
    private IPublicationService service;

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
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreatePublicationRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable @Valid Long id, @RequestBody UpdatePublicationRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
