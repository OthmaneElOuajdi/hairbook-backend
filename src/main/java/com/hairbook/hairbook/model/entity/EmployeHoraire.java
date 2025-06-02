package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employe_horaire")
public class EmployeHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "horaire_id", nullable = false)
    private HoraireDisponible horaire;
    
    // Constructeurs
    public EmployeHoraire() {
    }
    
    public EmployeHoraire(Integer id, Employe employe, HoraireDisponible horaire) {
        this.id = id;
        this.employe = employe;
        this.horaire = horaire;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Employe getEmploye() {
        return employe;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    public HoraireDisponible getHoraire() {
        return horaire;
    }
    
    public void setHoraire(HoraireDisponible horaire) {
        this.horaire = horaire;
    }
}

