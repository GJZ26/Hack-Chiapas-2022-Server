package com.example.demohack.repositories;

import com.example.demohack.entities.Category;
import com.example.demohack.entities.Product;
import com.example.demohack.entities.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long>{
    @Query(value = "select * from product where company_id = :id",nativeQuery = true)
    List<ProductProjection> getAllProductsByCompanyId (Long id);
    
}
