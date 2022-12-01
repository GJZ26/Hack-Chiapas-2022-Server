package com.example.demohack.repositories;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> relation/publications-comments

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demohack.entities.Comment;
import com.example.demohack.entities.projections.CommentProjection;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long>{
<<<<<<< HEAD
    @Query(value="SELECT * FROM comments WHERE user_id=:idUser", nativeQuery = true)
    List<CommentProjection> findByIdUser(Long idUser);
=======
    @Query(value="SELECT * FROM comments WHERE user_id=:userId", nativeQuery = true)
    List<CommentProjection> listAllCommentsByUserId(Long userId);
    
    @Query(value="SELECT * FROM comments WHERE publication_id=:publicationId", nativeQuery = true)
    List<CommentProjection> listAllCommentsByPublicationId(Long publicationId);
>>>>>>> relation/publications-comments
    
}