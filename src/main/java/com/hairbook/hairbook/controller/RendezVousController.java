package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.dto.RendezVousDTO;
import com.hairbook.hairbook.service.RendezVousService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        List<RendezVousDTO> rendezVous = rendezVousService.findAllDTO();
        return ResponseEntity.ok(rendezVous);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isRendezVousOwner(#id)")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Integer id) {
        return rendezVousService.findDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(#utilisateurId)")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByUtilisateur(@PathVariable Integer utilisateurId) {
        List<RendezVousDTO> rendezVous = rendezVousService.findByUtilisateurId(utilisateurId);
        return ResponseEntity.ok(rendezVous);
    }

    @GetMapping("/utilisateur/{utilisateurId}/futurs")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(#utilisateurId)")
    public ResponseEntity<List<RendezVousDTO>> getFutureRendezVousByUtilisateur(@PathVariable Integer utilisateurId) {
        List<RendezVousDTO> rendezVous = rendezVousService.findFutureRendezVousByUtilisateurId(utilisateurId);
        return ResponseEntity.ok(rendezVous);
    }

    @PostMapping
    public ResponseEntity<RendezVousDTO> createRendezVous(@RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO createdRendezVous = rendezVousService.createRendezVous(rendezVousDTO);
        return ResponseEntity.ok(createdRendezVous);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RendezVousDTO> updateRendezVousStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        RendezVousDTO updatedRendezVous = rendezVousService.updateStatus(id, status);
        return ResponseEntity.ok(updatedRendezVous);
    }

    @DeleteMapping("/{id}/annuler")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isRendezVousOwner(#id)")
    public ResponseEntity<Void> annulerRendezVous(@PathVariable Integer id) {
        rendezVousService.annulerRendezVous(id);
        return ResponseEntity.ok().build();
    }
}
