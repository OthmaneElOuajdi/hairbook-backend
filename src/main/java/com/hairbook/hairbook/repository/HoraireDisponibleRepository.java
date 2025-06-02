package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.HoraireDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoraireDisponibleRepository extends JpaRepository<HoraireDisponible, Integer> {
    List<HoraireDisponible> findByJour(LocalDate jour);
    
    @Query("SELECT h FROM HoraireDisponible h WHERE h.jour >= ?1 ORDER BY h.jour, h.heureDebut")
    List<HoraireDisponible> findFutureHoraires(LocalDate dateDebut);
}
