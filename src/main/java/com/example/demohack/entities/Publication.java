package com.example.demohack.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Publication {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String creationDate;
    private String image;
    @OneToMany(mappedBy = "publication")
    private List<Comment> comments;
}
