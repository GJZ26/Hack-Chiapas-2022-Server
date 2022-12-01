package com.example.demohack.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demohack.controllers.dtos.request.CreateCommentRequest;
import com.example.demohack.controllers.dtos.request.UpdateCommentRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreateUserResponse;
import com.example.demohack.controllers.dtos.response.GetCommentResponse;
import com.example.demohack.entities.Comment;
import com.example.demohack.entities.User;
import com.example.demohack.repositories.ICommentRepository;
import com.example.demohack.services.interfaces.ICommentService;
import com.example.demohack.services.interfaces.IUserService;

@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private ICommentRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public BaseResponse get(Long id) {
        GetCommentResponse response=from(id);
        return BaseResponse.builder()
            .data(response)
            .message("Comment has been found")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetCommentResponse> response=repository.findAll()
            .stream()
            .map(this::from)
            .collect(Collectors.toList());
        return  BaseResponse.builder()
        .data(response)
        .message("Comments have been getted")
        .success(Boolean.TRUE)
        .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateCommentRequest request) {
        Comment comment= from(request);
        GetCommentResponse response= from(comment);

        return BaseResponse.builder()
                .data(response)
                .message("Tag created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public BaseResponse update(Long id, UpdateCommentRequest request) {
        Comment comment=repository.findById(id).orElseThrow(()-> new RuntimeException("The comment does not exist"));
        comment=update(comment, request);
        GetCommentResponse response = from(comment);
        return BaseResponse.builder()
        .data(response)
        .message("Comment updated correctly")
        .success(Boolean.TRUE)
        .httpStatus(HttpStatus.OK).build();
    }

    private Comment update(Comment comment, UpdateCommentRequest request){
        comment.setComment(request.getComment());
        comment.setDate(getDate());
        comment.setStatus("Edit");
        return repository.save(comment);
    }

    private GetCommentResponse from(Long id){
        return repository.findById(id).
        map(this::from)
        .orElseThrow(()-> new RuntimeException("The comment does not exist"));
    }

    private GetCommentResponse from (Comment comment){
        GetCommentResponse response = new GetCommentResponse();
        response.setComment(comment.getComment());
        response.setDate(comment.getDate());
        response.setEmail(comment.getEmail());
        response.setId(comment.getId());
        response.setStatus(comment.getStatus());
        response.setUser(from(comment.getUser()));
        return response;
    }

    private CreateUserResponse from (User request) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(request.getId());
        response.setEmail(request.getEmail());
        response.setUsername(request.getUsername());

        return response;
    }

    private Comment from (CreateCommentRequest request){
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setEmail(request.getEmail());
        comment.setDate(getDate());
        comment.setStatus("origin");
        comment.setUser(userService.findById(request.getUserId()));
        return repository.save(comment);
    }

    private String getDate(){
        LocalDateTime dateNow= LocalDateTime.now();
        String date=dateNow.format(getFormat());
        return date;
    }

    private DateTimeFormatter getFormat(){
        DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return format;
    }
    
    
}
