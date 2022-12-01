package com.example.demohack.services;

import com.example.demohack.controllers.dtos.request.CreatePublicationRequest;
import com.example.demohack.controllers.dtos.request.UpdatePublicationRequest;
import com.example.demohack.controllers.dtos.response.BaseResponse;
import com.example.demohack.controllers.dtos.response.CreatePublicationResponse;
import com.example.demohack.entities.Publication;
import com.example.demohack.repositories.IPublicationRepository;
import com.example.demohack.services.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements IPublicationService {
    @Autowired
    private IPublicationRepository repository;
    @Override
    public BaseResponse get(Long id) {
        Optional<Publication> optionalPublication = repository.findById(id);

        if (optionalPublication.isPresent()) {
            CreatePublicationResponse response = from(optionalPublication.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Publication by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The publication with id: "+ id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreatePublicationResponse> responses = new ArrayList<>();
        List<Publication> repositoryAll = repository.findAll();

        for (Publication publications : repositoryAll) {
            responses.add(from(publications));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all Publications")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreatePublicationRequest request) {
        Publication publicationRepository = repository.save(from(request));
        CreatePublicationResponse response = from(publicationRepository);
        return BaseResponse.builder()
                .data(response)
                .message("Publication Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdatePublicationRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<Publication> optionalPublication = repository.findById(id);

        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            CreatePublicationResponse response = from(update(publication, request));

            return BaseResponse.builder()
                    .data(response)
                    .message("Publication updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Publication with id: "+ id + " doesn't exist");
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Publication findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("The Publication with id: "+ id + " doesn't exist"));
    }

    private Publication from (CreatePublicationRequest request) {
        Publication response = new Publication();
        response.setCreationDate(request.getCreationDate());
        response.setImage(request.getImage());
        response.setTitle(request.getTitle());
        response.setText(request.getText());
        return response;

    }

    private CreatePublicationResponse from (Publication publication) {
        CreatePublicationResponse response = new CreatePublicationResponse();
        response.setId(publication.getId());
        response.setCreationDate(publication.getCreationDate());
        response.setImage(publication.getImage());
        response.setTitle(publication.getTitle());
        response.setText(publication.getText());
        return response;
    }

    private Publication update (Publication publication, UpdatePublicationRequest request) {
        publication.setCreationDate(request.getCreationDate());
        publication.setImage(request.getImage());
        publication.setTitle(request.getTitle());
        publication.setText(request.getText());
        return repository.save(publication);
    }
}
