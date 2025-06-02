package com.hairbook.hairbook.service;

import com.hairbook.hairbook.model.dto.ServiceDTO;
import com.hairbook.hairbook.model.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceCoiffureService extends BaseService<Service, Integer> {
    List<ServiceDTO> findAllDTO();
    Optional<ServiceDTO> findDTOById(Integer id);
}
