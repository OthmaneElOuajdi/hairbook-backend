package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "horaire_disponible")
public class HoraireDisponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate jour;

    @Column(name = "heure_debut", nullable = false)
    private String heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private String heureFin;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponible = true;

    @OneToMany(mappedBy = "horaire")
    private List<RendezVous> rendezVous;
    
    @OneToMany(mappedBy = "horaire")
    private List<EmployeHoraire> employes;
    
    // Constructeurs
    public HoraireDisponible() {
    }
    
    public HoraireDisponible(Integer id, LocalDate jour, String heureDebut, String heureFin, 
                           Boolean disponible, List<RendezVous> rendezVous, List<EmployeHoraire> employes) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.disponible = disponible;
        this.rendezVous = rendezVous;
        this.employes = employes;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDate getJour() {
        return jour;
    }
    
    public void setJour(LocalDate jour) {
        this.jour = jour;
    }
    
    public String getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public String getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
    
    public Boolean getDisponible() {
        return disponible;
    }
    
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
    
    public List<EmployeHoraire> getEmployes() {
        return employes;
    }
    
    public void setEmployes(List<EmployeHoraire> employes) {
        this.employes = employes;
    }
}

