package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;
    
    @Column
    private String biographie;
    
    @Column
    private String poste;
    
    @Column
    private String photo;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    
    @ManyToMany
    @JoinTable(
        name = "employe_service",
        joinColumns = @JoinColumn(name = "employe_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;
    
    @OneToMany(mappedBy = "employe")
    private List<EmployeHoraire> horaires;
    
    @OneToMany(mappedBy = "employe")
    private List<RendezVous> rendezVous;
    
    // Constructeurs
    public Employe() {
    }
    
    public Employe(Integer id, String nom, String biographie, String poste, String photo, Utilisateur utilisateur,
                  List<Service> services, List<EmployeHoraire> horaires, List<RendezVous> rendezVous) {
        this.id = id;
        this.nom = nom;
        this.biographie = biographie;
        this.poste = poste;
        this.photo = photo;
        this.utilisateur = utilisateur;
        this.services = services;
        this.horaires = horaires;
        this.rendezVous = rendezVous;
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
    
    public String getBiographie() {
        return biographie;
    }
    
    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
    
    public String getPoste() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public List<Service> getServices() {
        return services;
    }
    
    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    public List<EmployeHoraire> getHoraires() {
        return horaires;
    }
    
    public void setHoraires(List<EmployeHoraire> horaires) {
        this.horaires = horaires;
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
}

