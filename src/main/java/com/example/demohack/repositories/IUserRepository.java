package com.example.demohack.repositories;

import com.example.demohack.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query(value="SELECT * FROM users WHERE email=:email AND password=:password", nativeQuery = true)
    Optional<User> findByEmailAndPassword(String email, String password);
}
