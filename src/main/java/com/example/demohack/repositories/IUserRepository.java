package com.example.demohack.repositories;

import com.example.demohack.entities.Company;
import com.example.demohack.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
