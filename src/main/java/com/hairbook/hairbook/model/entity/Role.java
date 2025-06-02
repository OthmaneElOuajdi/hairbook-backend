package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nom;
    
    @Column
    private String description;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;
    
    public Role() {
    }
    
    public Role(Integer id, String nom, String description, List<Utilisateur> utilisateurs) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.utilisateurs = utilisateurs;
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
    
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
    
    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}

