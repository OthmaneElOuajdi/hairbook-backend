package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.entity.Employe;
import com.hairbook.hairbook.repository.EmployeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeRepository employeRepository;

    public EmployeController(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeRepository.findAll();
        return ResponseEntity.ok(employes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Integer id) {
        Optional<Employe> employe = employeRepository.findById(id);
        return employe.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
        Employe createdEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(createdEmploye);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Integer id, @RequestBody Employe employe) {
        if (!employeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employe.setId(id);
        Employe updatedEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(updatedEmploye);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Integer id) {
        if (!employeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
