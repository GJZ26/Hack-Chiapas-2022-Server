package com.example.demohack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demohack.entities.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long>{
    @Query(value="SELECT * FROM comments WHERE user_id=:idUser", nativeQuery = true)
    Optional<Comment> findByIdUser(Long idUser);
    
}