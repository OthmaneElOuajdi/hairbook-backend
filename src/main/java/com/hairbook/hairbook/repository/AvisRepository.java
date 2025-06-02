package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Integer> {
    List<Avis> findByProduitId(Integer produitId);
    List<Avis> findByUtilisateurId(Integer utilisateurId);
}
