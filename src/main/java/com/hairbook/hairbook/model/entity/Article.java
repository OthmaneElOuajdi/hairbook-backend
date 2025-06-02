package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;
    
    @OneToMany(mappedBy = "article")
    private List<ArticleCategorie> categories;
    
    @OneToMany(mappedBy = "article")
    private List<ArticleMotCle> motCles;
}
