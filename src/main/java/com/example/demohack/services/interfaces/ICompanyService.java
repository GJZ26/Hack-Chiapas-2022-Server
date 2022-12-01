package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.entities.Company;

public interface ICompanyService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateCompanyRequest request);

    BaseResponse update (Long id, UpdateCompanyRequest request);

    void delete (Long id);

    Company getCompanyById(Long id);
}
