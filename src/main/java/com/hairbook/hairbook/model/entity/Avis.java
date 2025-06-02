package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private Integer note;

    @Column
    private String commentaire;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    // Constructeurs
    public Avis() {
    }
    
    public Avis(Integer id, Utilisateur utilisateur, Produit produit, Integer note, String commentaire, LocalDateTime dateCreation) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.produit = produit;
        this.note = note;
        this.commentaire = commentaire;
        this.dateCreation = dateCreation;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public Produit getProduit() {
        return produit;
    }
    
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public Integer getNote() {
        return note;
    }
    
    public void setNote(Integer note) {
        this.note = note;
    }
    
    public String getCommentaire() {
        return commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
    }
}

