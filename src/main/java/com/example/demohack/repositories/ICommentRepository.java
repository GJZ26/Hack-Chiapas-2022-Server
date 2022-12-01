package com.example.demohack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demohack.entities.Comment;
import com.example.demohack.entities.projections.CommentProjection;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long>{
    @Query(value="SELECT * FROM comments WHERE user_id=:idUser", nativeQuery = true)
    List<CommentProjection> findByIdUser(Long idUser);
    
}