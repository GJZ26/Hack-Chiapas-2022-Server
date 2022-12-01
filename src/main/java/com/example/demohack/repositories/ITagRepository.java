package com.example.demohack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demohack.entities.Tag;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long>{

    
}