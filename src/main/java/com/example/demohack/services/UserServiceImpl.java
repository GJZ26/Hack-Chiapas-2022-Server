package com.example.demohack.services;

import com.example.demohack.controllers.dtos.request.CreateUserRequest;
import com.example.demohack.controllers.dtos.request.UpdateUserRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreateUserResponse;
import com.example.demohack.entities.User;
import com.example.demohack.repositories.IUserRepository;
import com.example.demohack.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;
    @Override
    public BaseResponse get(Long id) {
        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isPresent()) {
            CreateUserResponse response = from(optionalUser.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("User by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The User with id: "+ id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateUserResponse> responses = new ArrayList<>();
        List<User> repositoryAll = repository.findAll();

        for (User users : repositoryAll) {
            responses.add(from(users));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all users")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User userRepository = repository.save(from(request));
        CreateUserResponse response = from(userRepository);
        return BaseResponse.builder()
                .data(response)
                .message("User Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            CreateUserResponse response = from(update(user, request));

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

    private User from(CreateUserRequest request) {
        User response = new User();
        response.setEmail(request.getEmail());
        response.setPassword(request.getPassword());
        response.setUsername(request.getUsername());
        return response;
    }

    private CreateUserResponse from (User request) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(request.getId());
        response.setEmail(request.getEmail());
        response.setPassword(request.getPassword());
        response.setUsername(request.getUsername());

        return response;
    }

    private User update (User user, UpdateUserRequest request) {
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        return repository.save(user);
    }
}
