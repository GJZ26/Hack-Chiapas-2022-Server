package com.example.demohack.services;

import com.example.demohack.controllers.dtos.request.CreateCompanyRequest;
import com.example.demohack.controllers.dtos.request.UpdateCompanyRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreateCompanyResponse;
import com.example.demohack.entities.Category;
import com.example.demohack.entities.Company;
import com.example.demohack.entities.projections.CompanyProjection;
import com.example.demohack.repositories.ICompanyRepository;
import com.example.demohack.services.interfaces.ICategoryService;
import com.example.demohack.services.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demohack.controllers.dtos.response.GetCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyRepository repository;

    @Autowired 
    private ICategoryService categoryService;
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

    @Override
    public Company getCompanyById(Long id) {
        return repository.findById(id).orElseThrow( ()-> new RuntimeException("The company with the id "+ id +" doesn't exists") );
    }

    private Company from (CreateCompanyRequest request) {
        Company response = new Company();
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setAddress(request.getAddress());
        response.setRegister_date(request.getRegister_date());
        response.setCategory(categoryService.getbyId(request.getCategoryId()));
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
        response.setCategory(from(request.getCategory()));
        response.setPhoto(request.getPhoto());
        return response;
    }

    private GetCategoryResponse from(Category category){
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
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

    @Override
    public BaseResponse listAllCompaniesByIdCategory(Long categoryId) {
        List<CompanyProjection>comments= repository.listAllCompaniesByCategoryId(categoryId);
        List<CreateCompanyResponse> response= comments.stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
            .data(response)
            .message("Comment has been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    private CreateCompanyResponse from (CompanyProjection request) {
        CreateCompanyResponse response = new CreateCompanyResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response.setAddress(request.getAddress());
        response.setRegister_date(request.getRegister_date());
        response.setCategory(from(categoryService.getbyId(request.getCategory_id())));
        response.setPhoto(request.getPhoto());
        return response;
    }


}
