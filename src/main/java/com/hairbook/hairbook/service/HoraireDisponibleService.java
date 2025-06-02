package com.hairbook.hairbook.service;

import com.hairbook.hairbook.model.dto.HoraireDisponibleDTO;
import com.hairbook.hairbook.model.entity.HoraireDisponible;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HoraireDisponibleService extends BaseService<HoraireDisponible, Integer> {
    List<HoraireDisponibleDTO> findAllDTO();
    Optional<HoraireDisponibleDTO> findDTOById(Integer id);
    List<HoraireDisponibleDTO> findByJour(LocalDate jour);
    List<HoraireDisponibleDTO> findFutureHoraires();
    boolean isHoraireDisponible(Integer horaireId);
}
