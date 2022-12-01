package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.CreatePublicationRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdatePublicationRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;

public interface IPublicationService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreatePublicationRequest request);

    BaseResponse update (Long id, UpdatePublicationRequest request);

    void delete (Long id);
}
