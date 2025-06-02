package com.hairbook.hairbook.service.impl;

import com.hairbook.hairbook.model.dto.ServiceDTO;
import com.hairbook.hairbook.model.entity.Service;
import com.hairbook.hairbook.repository.ServiceRepository;
import com.hairbook.hairbook.service.ServiceCoiffureService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceCoiffureServiceImpl extends BaseServiceImpl<Service, Integer> implements ServiceCoiffureService {

    private final ServiceRepository serviceRepository;

    public ServiceCoiffureServiceImpl(ServiceRepository serviceRepository) {
        super(serviceRepository);
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDTO> findAllDTO() {
        return serviceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDTO> findDTOById(Integer id) {
        return serviceRepository.findById(id)
                .map(this::convertToDTO);
    }

    private ServiceDTO convertToDTO(Service service) {
        return new ServiceDTO(
                service.getId(),
                service.getNom(),
                service.getDescription(),
                service.getDuree(),
                service.getPrix()
        );
    }
}
