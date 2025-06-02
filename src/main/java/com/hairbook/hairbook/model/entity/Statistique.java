package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "statistique")
public class Statistique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "nb_clients_semaine")
    private Integer nbClientsSemaine;

    @Column(name = "taux_occupation")
    private BigDecimal tauxOccupation;

    @Column(name = "creneaux_populaires", columnDefinition = "TEXT")
    private String creneauxPopulaires;
    
    // Constructeurs
    public Statistique() {
    }
    
    public Statistique(Integer id, Integer adminId, Integer nbClientsSemaine, 
                     BigDecimal tauxOccupation, String creneauxPopulaires) {
        this.id = id;
        this.adminId = adminId;
        this.nbClientsSemaine = nbClientsSemaine;
        this.tauxOccupation = tauxOccupation;
        this.creneauxPopulaires = creneauxPopulaires;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getAdminId() {
        return adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
    
    public Integer getNbClientsSemaine() {
        return nbClientsSemaine;
    }
    
    public void setNbClientsSemaine(Integer nbClientsSemaine) {
        this.nbClientsSemaine = nbClientsSemaine;
    }
    
    public BigDecimal getTauxOccupation() {
        return tauxOccupation;
    }
    
    public void setTauxOccupation(BigDecimal tauxOccupation) {
        this.tauxOccupation = tauxOccupation;
    }
    
    public String getCreneauxPopulaires() {
        return creneauxPopulaires;
    }
    
    public void setCreneauxPopulaires(String creneauxPopulaires) {
        this.creneauxPopulaires = creneauxPopulaires;
    }
}

