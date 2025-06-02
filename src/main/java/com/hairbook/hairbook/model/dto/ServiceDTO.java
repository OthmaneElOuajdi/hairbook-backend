package com.hairbook.hairbook.model.dto;

import java.math.BigDecimal;

public class ServiceDTO {

    private Integer id;
    private String nom;
    private String description;
    private Integer duree;
    private BigDecimal prix;
    
    public ServiceDTO() {
    }
    
    public ServiceDTO(Integer id, String nom, String description, Integer duree, BigDecimal prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.prix = prix;
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
}
