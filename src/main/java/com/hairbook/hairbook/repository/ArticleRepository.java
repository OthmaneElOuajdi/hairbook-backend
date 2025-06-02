package com.hairbook.hairbook.repository;

import com.hairbook.hairbook.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    
    @Query("SELECT a FROM Article a JOIN a.categories ac WHERE ac.categorie.id = ?1")
    List<Article> findByCategorieId(Integer categorieId);
    
    @Query("SELECT a FROM Article a JOIN a.motCles amc WHERE amc.motCle.id = ?1")
    List<Article> findByMotCleId(Integer motCleId);
}
