package com.example.demohack.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demohack.controllers.dtos.request.CreateCategoryRequest;
import com.example.demohack.controllers.dtos.request.UpdateCategoryRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.GetCategoryResponse;
import com.example.demohack.entities.Category;
import com.example.demohack.repositories.ICategoryRepository;
import com.example.demohack.services.interfaces.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryRepository repository;


    @Override
    public BaseResponse get(Long id) {
        GetCategoryResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Category has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetCategoryResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Categories have been getted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateCategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(()-> new RuntimeException("The Category does not exist"));
        category=update(category, request);
        GetCategoryResponse response=from(category);
        return BaseResponse.builder()
                .data(response)
                .message("Category have been updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateCategoryRequest request) {
        Category category = from(request);
        GetCategoryResponse response= from(category);
        return BaseResponse.builder()
                .data(response)
                .message("Category created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();


    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);        
    }
    
    private Category update(Category category, UpdateCategoryRequest request){
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return repository.save(category);
    }    
    private Category from(CreateCategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return repository.save(category);
    }

    private GetCategoryResponse from(Long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(() -> new RuntimeException("The Category does not exist"));
            
    }

    private GetCategoryResponse from(Category category){
        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
    
}
