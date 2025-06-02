package com.hairbook.hairbook.model.dto;

import java.time.LocalDate;
import java.util.List;

public class HoraireDisponibleDTO {

    private Integer id;
    private LocalDate jour;
    private String heureDebut;
    private String heureFin;
    private List<Integer> employeIds;
    private boolean disponible;
    
    public HoraireDisponibleDTO() {
    }
    
    public HoraireDisponibleDTO(Integer id, LocalDate jour, String heureDebut, String heureFin, List<Integer> employeIds, boolean disponible) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.employeIds = employeIds;
        this.disponible = disponible;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDate getJour() {
        return jour;
    }
    
    public void setJour(LocalDate jour) {
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
    
    public List<Integer> getEmployeIds() {
        return employeIds;
    }
    
    public void setEmployeIds(List<Integer> employeIds) {
        this.employeIds = employeIds;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
