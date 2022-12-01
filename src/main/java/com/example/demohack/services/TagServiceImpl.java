package com.example.demohack.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demohack.controllers.dtos.request.CreateTagRequest;
import com.example.demohack.controllers.dtos.request.UpdateTagRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.GetTagResponse;
import com.example.demohack.entities.Tag;
import com.example.demohack.repositories.ITagRepository;
import com.example.demohack.services.interfaces.ITagService;


@Service
public class TagServiceImpl implements ITagService{

    @Autowired
    private ITagRepository repository;

    @Override
    public BaseResponse get(Long id) {
        GetTagResponse response=from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Tag has been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        List<GetTagResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Tags have been getted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateTagRequest request) {
        Tag tag = repository.findById(id).orElseThrow(()-> new RuntimeException("The Tag does not exist"));
        tag=update(tag, request);
        GetTagResponse response=from(tag);
        return BaseResponse.builder()
                .data(response)
                .message("Tags have been updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateTagRequest request) {
        Tag tag = from(request);
        GetTagResponse response= from(tag);
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
    
    private Tag update(Tag tag, UpdateTagRequest request){
        tag.setTitle(request.getTitle());
        return repository.save(tag);
    }    
    private Tag from(CreateTagRequest request){
        Tag tag = new Tag();
        tag.setTitle(request.getTitle());
        return repository.save(tag);
    }

    private GetTagResponse from(Long id){
        return repository.findById(id)
            .map(this::from)
            .orElseThrow(() -> new RuntimeException("The Tag does not exist"));
            
    }

    private GetTagResponse from(Tag tag){
        GetTagResponse response = new GetTagResponse();
        response.setId(tag.getId());
        response.setTitle(tag.getTitle());
        return response;
    }
    

}
