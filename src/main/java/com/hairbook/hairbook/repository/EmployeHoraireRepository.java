package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.EmployeHoraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeHoraireRepository extends JpaRepository<EmployeHoraire, Integer> {
    List<EmployeHoraire> findByEmployeId(Integer employeId);
    
    List<EmployeHoraire> findByHoraireId(Integer horaireId);
    
    @Query("SELECT eh FROM EmployeHoraire eh WHERE eh.horaire.jour >= CURRENT_DATE ORDER BY eh.horaire.jour, eh.horaire.heureDebut")
    List<EmployeHoraire> findFutureHorairesWithEmployes();
}
