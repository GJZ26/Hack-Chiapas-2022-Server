package com.example.demohack.services;

import com.example.demohack.controllers.dtos.request.CreateProductRequest;
import com.example.demohack.controllers.dtos.request.CreateUserRequest;
import com.example.demohack.controllers.dtos.request.UpdateProductRequest;
import com.example.demohack.controllers.dtos.request.UpdateUserRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreateProductResponses;
import com.example.demohack.controllers.dtos.response.CreateUserResponse;
import com.example.demohack.entities.Product;
import com.example.demohack.entities.User;
import com.example.demohack.repositories.IProductRepository;
import com.example.demohack.repositories.IUserRepository;
import com.example.demohack.services.interfaces.IProductService;
import com.example.demohack.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository repository;
    @Override
    public BaseResponse get(Long id) {
        Optional<Product> optionalUser = repository.findById(id);

        if (optionalUser.isPresent()) {
            CreateProductResponses response = from(optionalUser.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Product by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Product with id: "+ id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateProductResponses> responses = new ArrayList<>();
        List<Product> repositoryAll = repository.findAll();

        for (Product users : repositoryAll) {
            responses.add(from(users));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all products")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateProductRequest request) {
        Product userRepository = repository.save(from(request));
        CreateProductResponses response = from(userRepository);
        return BaseResponse.builder()
                .data(response)
                .message("Product Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateProductRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<Product> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            Product user = userOptional.get();
            CreateProductResponses response = from( update(user, request) );

            return BaseResponse.builder()
                    .data(response)
                    .message("user updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The user with id: "+ id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Product from(CreateProductRequest request) {
        Product response = new Product();
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setPhoto(request.getPhoto());
        return response;
    }

    private CreateProductResponses from (Product request) {
        CreateProductResponses response = new CreateProductResponses();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setPhoto(request.getPhoto());
        return response;
    }

    private Product update (Product product, UpdateProductRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setPhoto(request.getPhoto());
        return repository.save(product);
    }
}
