package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.dto.HoraireDisponibleDTO;
import com.hairbook.hairbook.model.entity.HoraireDisponible;
import com.hairbook.hairbook.service.HoraireDisponibleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/horaires")
public class HoraireDisponibleController {

    private final HoraireDisponibleService horaireDisponibleService;

    public HoraireDisponibleController(HoraireDisponibleService horaireDisponibleService) {
        this.horaireDisponibleService = horaireDisponibleService;
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HoraireDisponibleDTO>> getFutureHoraires() {
        List<HoraireDisponibleDTO> horaires = horaireDisponibleService.findFutureHoraires();
        return ResponseEntity.ok(horaires);
    }

    @GetMapping("/jour/{date}")
    public ResponseEntity<List<HoraireDisponibleDTO>> getHorairesByJour(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<HoraireDisponibleDTO> horaires = horaireDisponibleService.findByJour(date);
        return ResponseEntity.ok(horaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoraireDisponibleDTO> getHoraireById(@PathVariable Integer id) {
        return horaireDisponibleService.findDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/disponible")
    public ResponseEntity<Boolean> isHoraireDisponible(@PathVariable Integer id) {
        boolean disponible = horaireDisponibleService.isHoraireDisponible(id);
        return ResponseEntity.ok(disponible);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HoraireDisponible> createHoraire(@RequestBody HoraireDisponible horaire) {
        HoraireDisponible createdHoraire = horaireDisponibleService.save(horaire);
        return ResponseEntity.ok(createdHoraire);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HoraireDisponible> updateHoraire(@PathVariable Integer id, @RequestBody HoraireDisponible horaire) {
        if (!horaireDisponibleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        horaire.setId(id);
        HoraireDisponible updatedHoraire = horaireDisponibleService.save(horaire);
        return ResponseEntity.ok(updatedHoraire);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHoraire(@PathVariable Integer id) {
        if (!horaireDisponibleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        horaireDisponibleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
