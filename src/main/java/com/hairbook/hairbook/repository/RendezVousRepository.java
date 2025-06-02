package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {
    List<RendezVous> findByUtilisateurId(Integer utilisateurId);
    
    /**
     * Trouve tous les rendez-vous futurs à partir d'une date donnée.
     * Utilise la date du jour de l'horaire au lieu du champ dateRdv qui a été supprimé.
     */
    @Query("SELECT r FROM RendezVous r JOIN r.horaire h WHERE h.jour >= ?1 ORDER BY h.jour, h.heureDebut")
    List<RendezVous> findFutureRendezVous(LocalDate dateDebut);
    
    /**
     * Trouve tous les rendez-vous futurs pour un utilisateur spécifique à partir d'une date donnée.
     * Utilise la date du jour de l'horaire au lieu du champ dateRdv qui a été supprimé.
     */
    @Query("SELECT r FROM RendezVous r JOIN r.horaire h WHERE r.utilisateur.id = ?1 AND h.jour >= ?2 ORDER BY h.jour, h.heureDebut")
    List<RendezVous> findFutureRendezVousByUtilisateur(Integer utilisateurId, LocalDate dateDebut);
    
    @Query("SELECT COUNT(r) FROM RendezVous r WHERE r.horaire.id = ?1")
    Long countByHoraireId(Integer horaireId);
    
    List<RendezVous> findByHoraireId(Integer horaireId);
}
