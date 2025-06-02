package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal prix;

    @Column
    private String image;
    
    @Column
    private String marque;
    
    @Column
    private String categorie;

    @Column(name = "stock_disponible")
    private Integer stockDisponible;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "produit")
    private List<Avis> avis;
    
    // Constructeurs
    public Produit() {
    }
    
    public Produit(Integer id, String nom, String description, BigDecimal prix, String image, String marque, 
                  String categorie, Integer stockDisponible, LocalDateTime createdAt, LocalDateTime updatedAt, 
                  List<Avis> avis) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.marque = marque;
        this.categorie = categorie;
        this.stockDisponible = stockDisponible;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.avis = avis;
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
    
    public BigDecimal getPrix() {
        return prix;
    }
    
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getMarque() {
        return marque;
    }
    
    public void setMarque(String marque) {
        this.marque = marque;
    }
    
    public String getCategorie() {
        return categorie;
    }
    
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public Integer getStockDisponible() {
        return stockDisponible;
    }
    
    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<Avis> getAvis() {
        return avis;
    }
    
    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

