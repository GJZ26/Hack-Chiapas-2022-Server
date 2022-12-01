package com.example.demohack.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
