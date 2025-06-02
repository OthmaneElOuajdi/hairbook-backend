package com.hairbook.hairbook.service;

import com.hairbook.hairbook.config.FileStorageConfig;
import com.hairbook.hairbook.exception.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Impossible de créer le répertoire de stockage des fichiers.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normaliser le nom du fichier
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        
        // Vérifier si le nom du fichier contient des caractères invalides
        if (originalFilename.contains("..")) {
            throw new RuntimeException("Désolé! Le nom du fichier contient une séquence de chemin invalide " + originalFilename);
        }

        // Générer un nom de fichier unique pour éviter les écrasements
        String fileExtension = "";
        if (originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

        try {
            // Copier le fichier dans le répertoire cible
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFilename;
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de stocker le fichier " + originalFilename + ". Veuillez réessayer!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("Fichier non trouvé " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ResourceNotFoundException("Fichier non trouvé: " + fileName);
        }
    }
}
