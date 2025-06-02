package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.dto.ServiceDTO;
import com.hairbook.hairbook.model.entity.Service;
import com.hairbook.hairbook.service.ServiceCoiffureService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCoiffureService serviceCoiffureService;

    public ServiceController(ServiceCoiffureService serviceCoiffureService) {
        this.serviceCoiffureService = serviceCoiffureService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceCoiffureService.findAllDTO();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Integer id) {
        return serviceCoiffureService.findDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        Service createdService = serviceCoiffureService.save(service);
        return ResponseEntity.ok(createdService);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Service> updateService(@PathVariable Integer id, @RequestBody Service service) {
        if (!serviceCoiffureService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.setId(id);
        Service updatedService = serviceCoiffureService.save(service);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (!serviceCoiffureService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceCoiffureService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
