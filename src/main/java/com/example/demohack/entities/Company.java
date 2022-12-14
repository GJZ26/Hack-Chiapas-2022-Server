package com.example.demohack.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private String register_date;
    private String photo;

    @OneToMany(mappedBy = "company")
    private List<Product> products;

    @ManyToOne
    private Category category;


}
