package com.hairbook.hairbook.service;

import com.hairbook.hairbook.model.dto.RendezVousDTO;
import com.hairbook.hairbook.model.entity.RendezVous;

import java.util.List;
import java.util.Optional;

public interface RendezVousService extends BaseService<RendezVous, Integer> {
    List<RendezVousDTO> findAllDTO();
    Optional<RendezVousDTO> findDTOById(Integer id);
    List<RendezVousDTO> findByUtilisateurId(Integer utilisateurId);
    List<RendezVousDTO> findFutureRendezVousByUtilisateurId(Integer utilisateurId);
    RendezVousDTO createRendezVous(RendezVousDTO rendezVousDTO);
    RendezVousDTO updateStatus(Integer id, String status);
    void annulerRendezVous(Integer id);
}
