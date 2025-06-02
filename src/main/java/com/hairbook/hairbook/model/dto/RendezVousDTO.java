package com.hairbook.hairbook.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RendezVousDTO {

    private Integer id;
    private Integer utilisateurId;
    private String utilisateurNom;
    private Integer employeId;
    private String employeNom;
    private Integer horaireId;
    private String jour;
    private String heureDebut;
    private String heureFin;
    private Integer serviceId;
    private String serviceNom;
    private BigDecimal servicePrix;
    private String statut;
    private LocalDateTime dateRdv;
    private LocalDateTime dateCreation;
    
    public RendezVousDTO() {
    }
    
    public RendezVousDTO(Integer id, Integer utilisateurId, String utilisateurNom, Integer employeId, String employeNom, 
                        Integer horaireId, String jour, String heureDebut, String heureFin, Integer serviceId, 
                        String serviceNom, BigDecimal servicePrix, String statut, LocalDateTime dateRdv, 
                        LocalDateTime dateCreation) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.utilisateurNom = utilisateurNom;
        this.employeId = employeId;
        this.employeNom = employeNom;
        this.horaireId = horaireId;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.serviceId = serviceId;
        this.serviceNom = serviceNom;
        this.servicePrix = servicePrix;
        this.statut = statut;
        this.dateRdv = dateRdv;
        this.dateCreation = dateCreation;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUtilisateurId() {
        return utilisateurId;
    }
    
    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    
    public String getUtilisateurNom() {
        return utilisateurNom;
    }
    
    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }
    
    public Integer getEmployeId() {
        return employeId;
    }
    
    public void setEmployeId(Integer employeId) {
        this.employeId = employeId;
    }
    
    public String getEmployeNom() {
        return employeNom;
    }
    
    public void setEmployeNom(String employeNom) {
        this.employeNom = employeNom;
    }
    
    public Integer getHoraireId() {
        return horaireId;
    }
    
    public void setHoraireId(Integer horaireId) {
        this.horaireId = horaireId;
    }
    
    public String getJour() {
        return jour;
    }
    
    public void setJour(String jour) {
        this.jour = jour;
    }
    
    public String getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public String getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
    
    public Integer getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getServiceNom() {
        return serviceNom;
    }
    
    public void setServiceNom(String serviceNom) {
        this.serviceNom = serviceNom;
    }
    
    public BigDecimal getServicePrix() {
        return servicePrix;
    }
    
    public void setServicePrix(BigDecimal servicePrix) {
        this.servicePrix = servicePrix;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public LocalDateTime getDateRdv() {
        return dateRdv;
    }
    
    public void setDateRdv(LocalDateTime dateRdv) {
        this.dateRdv = dateRdv;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
