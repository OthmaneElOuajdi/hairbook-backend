package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "horaire")
    private List<RendezVous> rendezVous;
    
    @OneToMany(mappedBy = "horaire")
    private List<EmployeHoraire> employes;
}
