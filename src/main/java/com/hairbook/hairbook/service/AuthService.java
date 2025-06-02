package com.hairbook.hairbook.service;

import com.hairbook.hairbook.model.dto.AuthRequestDTO;
import com.hairbook.hairbook.model.dto.AuthResponseDTO;
import com.hairbook.hairbook.model.dto.InscriptionDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
    AuthResponseDTO register(InscriptionDTO inscriptionDTO);
}
