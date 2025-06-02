package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.entity.Article;
import com.hairbook.hairbook.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<Article>> getArticlesByCategorie(@PathVariable Integer categorieId) {
        List<Article> articles = articleRepository.findByCategorieId(categorieId);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/mot-cle/{motCleId}")
    public ResponseEntity<List<Article>> getArticlesByMotCle(@PathVariable Integer motCleId) {
        List<Article> articles = articleRepository.findByMotCleId(motCleId);
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleRepository.save(article);
        return ResponseEntity.ok(createdArticle);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        if (!articleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        article.setId(id);
        Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        if (!articleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        articleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
