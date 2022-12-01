package com.example.demohack.services;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreateCompanyResponse;
import com.example.demohack.entities.Company;
import com.example.demohack.repositories.ICompanyRepository;
import com.example.demohack.services.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository repository;
    @Override
    public BaseResponse get(Long id) {
        Optional<Company> optionalCompany = repository.findById(id);

        if (optionalCompany.isPresent()) {
            CreateCompanyResponse response = from(optionalCompany.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Company by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The company with id: "+ id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateCompanyResponse> responses = new ArrayList<>();
        List<Company> repositoryAll = repository.findAll();

        for (Company companies : repositoryAll) {
            responses.add(from(companies));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all companies")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateCompanyRequest request) {
        Company companyRepository = repository.save(from(request));
        CreateCompanyResponse response = from(companyRepository);
        return BaseResponse.builder()
                .data(response)
                .message("Company Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateCompanyRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<Company> optionalCompany = repository.findById(id);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            CreateCompanyResponse response = from(update(company, request));

            return BaseResponse.builder()
                    .data(response)
                    .message("Company updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The company with id: "+ id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Company from (CreateCompanyRequest request) {
        Company response = new Company();
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setAddress(request.getAddress());
        response.setRegister_date(request.getRegister_date());
        response.setPhoto(request.getPhoto());

        return response;
    }

    private CreateCompanyResponse from (Company request) {
        CreateCompanyResponse response = new CreateCompanyResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setAddress(request.getAddress());
        response.setRegister_date(request.getRegister_date());
        response.setPhoto(request.getPhoto());
        return response;
    }

    private Company update (Company company, UpdateCompanyRequest request) {
        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setAddress(request.getAddress());
        company.setRegister_date(request.getRegister_date());
        company.setPhoto(request.getPhoto());
        return repository.save(company);
    }
}
