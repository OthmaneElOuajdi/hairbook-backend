package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nom;
    
    @Column
    private String description;

    @OneToMany(mappedBy = "categorie")
    private List<ArticleCategorie> articleCategories;
    
    // Constructeurs
    public Categorie() {
    }
    
    public Categorie(Integer id, String nom, String description, List<ArticleCategorie> articleCategories) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.articleCategories = articleCategories;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<ArticleCategorie> getArticleCategories() {
        return articleCategories;
    }
    
    public void setArticleCategories(List<ArticleCategorie> articleCategories) {
        this.articleCategories = articleCategories;
    }
}

