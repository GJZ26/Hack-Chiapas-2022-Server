package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateProductRequest;
import com.example.demohack.controllers.dtos.request.UpdateProductRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;

public interface IProductService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateProductRequest request);

    BaseResponse update (Long id, UpdateProductRequest request);

    void delete (Long id);

    BaseResponse getAllProductsByCompanyId(Long id);
}
