package com.hairbook.hairbook.controller;

import com.hairbook.hairbook.model.dto.AuthRequestDTO;
import com.hairbook.hairbook.model.dto.AuthResponseDTO;
import com.hairbook.hairbook.model.dto.InscriptionDTO;
import com.hairbook.hairbook.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody AuthRequestDTO loginRequest) {
        AuthResponseDTO response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody InscriptionDTO signUpRequest) {
        AuthResponseDTO response = authService.register(signUpRequest);
        return ResponseEntity.ok(response);
    }
}
