package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mot_cle")
public class MotCle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String mot;
    
    @OneToMany(mappedBy = "motCle")
    private List<ArticleMotCle> articles;

    // Constructeurs
    public MotCle() {
    }

    public MotCle(Integer id, String mot, List<ArticleMotCle> articles) {
        this.id = id;
        this.mot = mot;
        this.articles = articles;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public List<ArticleMotCle> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleMotCle> articles) {
        this.articles = articles;
    }
}

