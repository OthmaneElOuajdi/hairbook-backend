package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    List<Commentaire> findByArticleId(Integer articleId);
    List<Commentaire> findByUtilisateurId(Integer utilisateurId);
}
