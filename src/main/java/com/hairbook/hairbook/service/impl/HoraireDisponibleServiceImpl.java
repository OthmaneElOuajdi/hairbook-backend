package com.hairbook.hairbook.service.impl;

import com.hairbook.hairbook.model.dto.HoraireDisponibleDTO;
import com.hairbook.hairbook.model.entity.EmployeHoraire;
import com.hairbook.hairbook.model.entity.HoraireDisponible;
import com.hairbook.hairbook.model.entity.RendezVous;
import com.hairbook.hairbook.repository.EmployeHoraireRepository;
import com.hairbook.hairbook.repository.HoraireDisponibleRepository;
import com.hairbook.hairbook.repository.RendezVousRepository;
import com.hairbook.hairbook.service.HoraireDisponibleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class HoraireDisponibleServiceImpl extends BaseServiceImpl<HoraireDisponible, Integer> implements HoraireDisponibleService {

    private final HoraireDisponibleRepository horaireDisponibleRepository;
    private final EmployeHoraireRepository employeHoraireRepository;
    private final RendezVousRepository rendezVousRepository;

    public HoraireDisponibleServiceImpl(
            HoraireDisponibleRepository horaireDisponibleRepository,
            EmployeHoraireRepository employeHoraireRepository,
            RendezVousRepository rendezVousRepository) {
        super(horaireDisponibleRepository);
        this.horaireDisponibleRepository = horaireDisponibleRepository;
        this.employeHoraireRepository = employeHoraireRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public List<HoraireDisponibleDTO> findAllDTO() {
        return horaireDisponibleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HoraireDisponibleDTO> findDTOById(Integer id) {
        return horaireDisponibleRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<HoraireDisponibleDTO> findByJour(LocalDate jour) {
        return horaireDisponibleRepository.findByJour(jour).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HoraireDisponibleDTO> findFutureHoraires() {
        return horaireDisponibleRepository.findFutureHoraires(LocalDate.now()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isHoraireDisponible(Integer horaireId) {
        Optional<HoraireDisponible> horaire = horaireDisponibleRepository.findById(horaireId);
        if (horaire.isEmpty()) {
            return false;
        }
        
        // Vérifier si l'horaire est déjà réservé
        List<RendezVous> rendezVousList = rendezVousRepository.findByHoraireId(horaireId);
        return rendezVousList.isEmpty();
    }

    private HoraireDisponibleDTO convertToDTO(HoraireDisponible horaire) {
        // Récupérer les IDs des employés associés à cet horaire
        List<Integer> employeIds = employeHoraireRepository.findByHoraireId(horaire.getId())
                .stream()
                .map(EmployeHoraire::getEmploye)
                .map(employe -> employe.getId())
                .collect(Collectors.toList());
        
        // Vérifier si l'horaire est disponible (pas de rendez-vous associé)
        boolean disponible = rendezVousRepository.findByHoraireId(horaire.getId()).isEmpty();
        
        return new HoraireDisponibleDTO(
                horaire.getId(),
                horaire.getJour(),
                horaire.getHeureDebut(),
                horaire.getHeureFin(),
                employeIds,
                disponible
        );
    }
}
