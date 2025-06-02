package com.hairbook.hairbook.service;

import com.hairbook.hairbook.model.dto.UtilisateurDTO;
import com.hairbook.hairbook.model.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService extends BaseService<Utilisateur, Integer> {
    List<UtilisateurDTO> findAllDTO();
    Optional<UtilisateurDTO> findDTOById(Integer id);
    Optional<UtilisateurDTO> findDTOByEmail(String email);
    void demanderSuppression(Integer id);
}
