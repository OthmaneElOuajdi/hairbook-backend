package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer duree; // durée en minutes

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @OneToMany(mappedBy = "service")
    private List<RendezVous> rendezVous;
    
    public Service() {
    }
    
    public Service(Integer id, String nom, String description, Integer duree, BigDecimal prix, List<RendezVous> rendezVous) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
        this.rendezVous = rendezVous;
    }
    
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
    
    public Integer getDuree() {
        return duree;
    }
    
    public void setDuree(Integer duree) {
        this.duree = duree;
    }
    
    public BigDecimal getPrix() {
        return prix;
    }
    
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
}

