package com.hairbook.hairbook.service.impl;

import com.hairbook.hairbook.model.dto.UtilisateurDTO;
import com.hairbook.hairbook.model.entity.Utilisateur;
import com.hairbook.hairbook.repository.UtilisateurRepository;
import com.hairbook.hairbook.service.UtilisateurService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class UtilisateurServiceImpl extends BaseServiceImpl<Utilisateur, Integer> implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        super(utilisateurRepository);
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<UtilisateurDTO> findAllDTO() {
        return utilisateurRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UtilisateurDTO> findDTOById(Integer id) {
        return utilisateurRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public Optional<UtilisateurDTO> findDTOByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    @Override
    public void demanderSuppression(Integer id) {
        // Dans une implémentation réelle, vous pourriez marquer l'utilisateur pour suppression
        // ou envoyer une notification à l'administrateur
        // Pour cette version alpha, nous allons simplement simuler cette fonctionnalité
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            // Simuler une action de demande de suppression
            // Dans une version complète, vous pourriez ajouter un champ "suppressionDemandee" à l'entité
            System.out.println("Demande de suppression reçue pour l'utilisateur ID: " + id);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }

    private UtilisateurDTO convertToDTO(Utilisateur utilisateur) {
        return new UtilisateurDTO(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getRole().getNom(),
                utilisateur.getCreatedAt(),
                utilisateur.getUpdatedAt()
        );
    }
}
