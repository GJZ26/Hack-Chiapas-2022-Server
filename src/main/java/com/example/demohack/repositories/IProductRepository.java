package com.example.demohack.repositories;

import com.example.demohack.entities.Category;
import com.example.demohack.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long>{
    
}
