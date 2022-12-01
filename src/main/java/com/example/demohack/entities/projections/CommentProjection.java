
package com.example.demohack.entities.projections;

public interface CommentProjection {

    Long getId();
    String getComment();
    String getDate();
    String getEmail();
    String getStatus();
    Long getUser_id();
    Long getPublication_id();
}