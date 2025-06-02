package com.hairbook.hairbook.model.dto;

public class AuthResponseDTO {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String nom;
    private String email;
    private String role;
    
    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, String type, Integer id, String nom, String email, String role) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }

    public AuthResponseDTO(String token, Integer id, String nom, String email, String role) {
        this.token = token;
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.role = role;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
