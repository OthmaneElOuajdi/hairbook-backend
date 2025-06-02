package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "article_mot_cle")
public class ArticleMotCle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "motcle_id", nullable = false)
    private MotCle motCle;

    // Constructeurs
    public ArticleMotCle() {
    }

    public ArticleMotCle(Integer id, Article article, MotCle motCle) {
        this.id = id;
        this.article = article;
        this.motCle = motCle;
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

    public MotCle getMotCle() {
        return motCle;
    }

    public void setMotCle(MotCle motCle) {
        this.motCle = motCle;
    }
}

