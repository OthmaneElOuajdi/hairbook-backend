package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    // Méthodes personnalisées si j'en ai besoin
}
