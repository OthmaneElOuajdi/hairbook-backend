package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;

    @Column(name = "date_publication")
    private LocalDateTime datePublication;
    
    // Constructeurs
    public Commentaire() {
    }
    
    public Commentaire(Integer id, Article article, Utilisateur utilisateur, String contenu, LocalDateTime datePublication) {
        this.id = id;
        this.article = article;
        this.utilisateur = utilisateur;
        this.contenu = contenu;
        this.datePublication = datePublication;
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
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public LocalDateTime getDatePublication() {
        return datePublication;
    }
    
    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }
}

