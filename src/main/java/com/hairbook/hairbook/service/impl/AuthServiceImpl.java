package com.hairbook.hairbook.service.impl;

import com.hairbook.hairbook.model.dto.AuthRequestDTO;
import com.hairbook.hairbook.model.dto.AuthResponseDTO;
import com.hairbook.hairbook.model.dto.InscriptionDTO;
import com.hairbook.hairbook.model.entity.Role;
import com.hairbook.hairbook.model.entity.Utilisateur;
import com.hairbook.hairbook.repository.RoleRepository;
import com.hairbook.hairbook.repository.UtilisateurRepository;
import com.hairbook.hairbook.security.JwtUtils;
import com.hairbook.hairbook.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UtilisateurRepository utilisateurRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getMotDePasse()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return new AuthResponseDTO(
                jwt,
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getRole().getNom()
        );
    }

    @Override
    public AuthResponseDTO register(InscriptionDTO inscriptionDTO) {
        if (utilisateurRepository.existsByEmail(inscriptionDTO.getEmail())) {
            throw new RuntimeException("Erreur: Cet email est déjà utilisé!");
        }

        // Création d'un nouvel utilisateur avec le rôle CLIENT par défaut
        Role roleClient = roleRepository.findByNom("ROLE_CLIENT")
                .orElseThrow(() -> new RuntimeException("Erreur: Rôle ROLE_CLIENT non trouvé."));

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(inscriptionDTO.getNom());
        utilisateur.setEmail(inscriptionDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(inscriptionDTO.getMotDePasse()));
        utilisateur.setRole(roleClient);

        utilisateurRepository.save(utilisateur);

        // Connexion automatique après inscription
        AuthRequestDTO authRequestDTO = new AuthRequestDTO(
                inscriptionDTO.getEmail(),
                inscriptionDTO.getMotDePasse()
        );
        return login(authRequestDTO);
    }
}
