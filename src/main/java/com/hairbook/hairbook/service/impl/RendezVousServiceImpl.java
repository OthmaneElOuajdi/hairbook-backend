package com.hairbook.hairbook.service.impl;

import com.hairbook.hairbook.model.dto.RendezVousDTO;
import com.hairbook.hairbook.model.entity.Employe;
import com.hairbook.hairbook.model.entity.HoraireDisponible;
import com.hairbook.hairbook.model.entity.RendezVous;
import com.hairbook.hairbook.model.entity.Service;
import com.hairbook.hairbook.model.entity.Utilisateur;
import com.hairbook.hairbook.repository.EmployeRepository;
import com.hairbook.hairbook.repository.HoraireDisponibleRepository;
import com.hairbook.hairbook.repository.RendezVousRepository;
import com.hairbook.hairbook.repository.ServiceRepository;
import com.hairbook.hairbook.repository.UtilisateurRepository;
import com.hairbook.hairbook.service.RendezVousService;
// Import supprimé pour éviter le conflit avec l'entité Service
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class RendezVousServiceImpl extends BaseServiceImpl<RendezVous, Integer> implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ServiceRepository serviceRepository;
    private final HoraireDisponibleRepository horaireDisponibleRepository;
    private final EmployeRepository employeRepository;

    public RendezVousServiceImpl(
            RendezVousRepository rendezVousRepository,
            UtilisateurRepository utilisateurRepository,
            ServiceRepository serviceRepository,
            HoraireDisponibleRepository horaireDisponibleRepository,
            EmployeRepository employeRepository) {
        super(rendezVousRepository);
        this.rendezVousRepository = rendezVousRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.serviceRepository = serviceRepository;
        this.horaireDisponibleRepository = horaireDisponibleRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public List<RendezVousDTO> findAllDTO() {
        return rendezVousRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RendezVousDTO> findDTOById(Integer id) {
        return rendezVousRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<RendezVousDTO> findByUtilisateurId(Integer utilisateurId) {
        return rendezVousRepository.findByUtilisateurId(utilisateurId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RendezVousDTO> findFutureRendezVousByUtilisateurId(Integer utilisateurId) {
        return rendezVousRepository.findFutureRendezVousByUtilisateur(utilisateurId, LocalDate.now()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RendezVousDTO createRendezVous(RendezVousDTO rendezVousDTO) {
        // Vérifier si l'horaire est disponible
        List<RendezVous> existingRendezVous = rendezVousRepository.findByHoraireId(rendezVousDTO.getHoraireId());
        if (!existingRendezVous.isEmpty()) {
            throw new RuntimeException("Cet horaire n'est plus disponible");
        }

        // Récupérer les entités nécessaires
        Utilisateur utilisateur = utilisateurRepository.findById(rendezVousDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        Service service = serviceRepository.findById(rendezVousDTO.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service non trouvé"));
        
        HoraireDisponible horaire = horaireDisponibleRepository.findById(rendezVousDTO.getHoraireId())
                .orElseThrow(() -> new RuntimeException("Horaire non trouvé"));
        
        Employe employe = employeRepository.findById(rendezVousDTO.getEmployeId())
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        // Créer le rendez-vous
        RendezVous rendezVous = new RendezVous();
        rendezVous.setUtilisateur(utilisateur);
        rendezVous.setService(service);
        rendezVous.setHoraire(horaire);
        rendezVous.setEmploye(employe);
        rendezVous.setStatut("CONFIRMÉ");
        rendezVous.setDateCreation(LocalDateTime.now());

        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(savedRendezVous);
    }

    @Override
    public RendezVousDTO updateStatus(Integer id, String status) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé"));
        
        rendezVous.setStatut(status);
        RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(updatedRendezVous);
    }

    @Override
    public void annulerRendezVous(Integer id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé"));
        
        // Vérifier si le rendez-vous peut être annulé (par exemple, s'il n'est pas déjà passé)
        HoraireDisponible horaire = rendezVous.getHoraire();
        LocalDateTime dateRendezVous = LocalDateTime.of(
                horaire.getJour(), 
                java.time.LocalTime.parse(horaire.getHeureDebut())
        );
        
        if (dateRendezVous.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Impossible d'annuler un rendez-vous passé");
        }
        
        // Mettre à jour le statut
        rendezVous.setStatut("ANNULÉ");
        rendezVousRepository.save(rendezVous);
    }

    private RendezVousDTO convertToDTO(RendezVous rendezVous) {
        RendezVousDTO dto = new RendezVousDTO();
        dto.setId(rendezVous.getId());
        dto.setUtilisateurId(rendezVous.getUtilisateur().getId());
        dto.setUtilisateurNom(rendezVous.getUtilisateur().getNom());
        dto.setEmployeId(rendezVous.getEmploye().getId());
        dto.setEmployeNom(rendezVous.getEmploye().getNom());
        dto.setServiceId(rendezVous.getService().getId());
        dto.setServiceNom(rendezVous.getService().getNom());
        dto.setServicePrix(rendezVous.getService().getPrix());
        dto.setHoraireId(rendezVous.getHoraire().getId());
        dto.setJour(rendezVous.getHoraire().getJour().toString());
        dto.setHeureDebut(rendezVous.getHoraire().getHeureDebut());
        dto.setHeureFin(rendezVous.getHoraire().getHeureFin());
        dto.setStatut(rendezVous.getStatut());
        // Utiliser les informations de l'horaire pour déterminer la date du rendez-vous
        // car le champ dateRdv n'existe plus dans l'entité
        // Comme heureDebut est un String, nous devons le parser en LocalTime
        LocalDateTime dateRdv = LocalDateTime.of(
            rendezVous.getHoraire().getJour(), 
            java.time.LocalTime.parse(rendezVous.getHoraire().getHeureDebut()));
        dto.setDateRdv(dateRdv);
        dto.setDateCreation(rendezVous.getDateCreation());
        return dto;
    }
}
