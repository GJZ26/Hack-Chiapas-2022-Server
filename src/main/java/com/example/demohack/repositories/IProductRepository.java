package com.example.demohack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demohack.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long>{
    
}
