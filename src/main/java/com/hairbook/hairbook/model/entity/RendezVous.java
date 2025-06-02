package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "horaire_id", nullable = false)
    private HoraireDisponible horaire;

    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(nullable = false)
    private String statut; // "CONFIRMÉ", "ANNULÉ", "EN_ATTENTE"

    @Column
    private String commentaire;
    

    @Column(name = "created_at")
    private LocalDateTime dateCreation;
    
    // Constructeurs
    public RendezVous() {
    }
    
    public RendezVous(Integer id, Utilisateur utilisateur, HoraireDisponible horaire, Employe employe, 
                     Service service, String statut, String commentaire, LocalDateTime dateCreation) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.horaire = horaire;
        this.employe = employe;
        this.service = service;
        this.statut = statut;
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
    
    public HoraireDisponible getHoraire() {
        return horaire;
    }
    
    public void setHoraire(HoraireDisponible horaire) {
        this.horaire = horaire;
    }
    
    public Employe getEmploye() {
        return employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    public Service getService() {
        return service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public String getCommentaire() {
        return commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    // Les méthodes getDateRdv et setDateRdv ont été supprimées car la colonne date_rdv n'existe pas dans la base de données
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}

