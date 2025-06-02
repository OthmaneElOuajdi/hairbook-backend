package com.hairbook.hairbook.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utilitaire pour initialiser la base de données directement via JDBC
 */
public class DatabaseInitializer {

    // Configuration de la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "hairbook";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    public static void main(String[] args) {
        System.out.println("===== Initialisation de la base de données HairBook =====\n");
        
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Créer la base de données si elle n'existe pas
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                System.out.println("Création de la base de données " + DB_NAME + " si elle n'existe pas...");
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
                System.out.println("Base de données créée ou déjà existante.\n");
            }
            
            // Exécuter le script SQL
            String sqlFilePath = "src/main/resources/hairbook_final_dump.sql";
            System.out.println("Exécution du script SQL: " + sqlFilePath);
            
            // Lire le contenu du fichier SQL
            String sqlScript = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
            
            // Se connecter à la base de données et exécuter le script
            try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                // Diviser le script en instructions individuelles
                String[] sqlInstructions = sqlScript.split(";");
                
                for (String instruction : sqlInstructions) {
                    if (!instruction.trim().isEmpty()) {
                        try {
                            stmt.execute(instruction);
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de l'exécution de l'instruction SQL: " + e.getMessage());
                            System.err.println("Instruction problématique: " + instruction);
                            // Continuer malgré l'erreur
                        }
                    }
                }
                
                System.out.println("\nScript SQL exécuté avec succès!");
            }
            
            System.out.println("\nBase de données initialisée avec succès!");
            System.out.println("\nVous pouvez maintenant démarrer l'application normalement sans le profil db-init.");
            System.out.println("Pour démarrer l'application, exécutez: mvn spring-boot:run");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur: Driver MySQL JDBC non trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
