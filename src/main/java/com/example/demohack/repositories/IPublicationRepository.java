package com.example.demohack.repositories;

import com.example.demohack.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicationRepository extends JpaRepository<Publication, Long> {
}
