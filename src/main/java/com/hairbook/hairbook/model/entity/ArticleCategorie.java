package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "article_categorie")
public class ArticleCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    // Constructeurs
    public ArticleCategorie() {
    }

    public ArticleCategorie(Integer id, Article article, Categorie categorie) {
        this.id = id;
        this.article = article;
        this.categorie = categorie;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}

