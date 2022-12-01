package com.example.demohack.services.interfaces;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.CreateUserRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdateUserRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;

public interface IUserService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateUserRequest request);

    BaseResponse update (Long id, UpdateUserRequest request);

    void delete (Long id);
}
