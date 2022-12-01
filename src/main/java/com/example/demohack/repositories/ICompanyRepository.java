package com.example.demohack.repositories;

import com.example.demohack.entities.Company;
import com.example.demohack.entities.projections.CompanyProjection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {

    @Query(value="SELECT * FROM company WHERE category_id=:categoryId", nativeQuery = true)
    List<CompanyProjection> listAllCompaniesByCategoryId(Long categoryId);
}

