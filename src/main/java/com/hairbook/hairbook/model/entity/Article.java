package com.hairbook.hairbook.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;
    
    @Column(name = "date_publication")
    private LocalDateTime datePublication;
    
    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;
    
    @Column
    private String image;
    
    @OneToMany(mappedBy = "article")
    private List<ArticleCategorie> categories;
    
    @OneToMany(mappedBy = "article")
    private List<ArticleMotCle> motCles;
    
    /**
     * Constructeur par défaut
     */
    public Article() {
    }
    
    /**
     * Constructeur avec tous les arguments
     */
    public Article(Integer id, String titre, String contenu, LocalDateTime datePublication, 
                  Utilisateur auteur, String image, List<ArticleCategorie> categories, 
                  List<ArticleMotCle> motCles) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.auteur = auteur;
        this.image = image;
        this.categories = categories;
        this.motCles = motCles;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public LocalDateTime getDatePublication() {
        return datePublication;
    }
    
    public Utilisateur getAuteur() {
        return auteur;
    }
    
    public String getImage() {
        return image;
    }
    
    public List<ArticleCategorie> getCategories() {
        return categories;
    }
    
    public List<ArticleMotCle> getMotCles() {
        return motCles;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }
    
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public void setCategories(List<ArticleCategorie> categories) {
        this.categories = categories;
    }
    
    public void setMotCles(List<ArticleMotCle> motCles) {
        this.motCles = motCles;
    }
}

