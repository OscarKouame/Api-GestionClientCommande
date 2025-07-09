package com.testorangeci.gestcom.controller;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testorangeci.gestcom.dtos.ClientDTO;
import com.testorangeci.gestcom.services.GestComService;

/**
 * Contrôleur REST pour la gestion des clients.
 * Fournit des endpoints pour les opérations CRUD sur les clients.
 */
@RestController
@AllArgsConstructor
@Slf4j
public class ClientController {

    private final GestComService gestComService;

    /**
     * Enregistre un nouveau client.
     *
     * @param clientDTO les informations du client à créer (validées)
     * @return le client créé au format DTO
     */
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(gestComService.saveClient(clientDTO));
    }

    /**
     * Récupère la liste de tous les clients enregistrés.
     *
     * @return une liste de clients sous forme de DTO
     */
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        return ResponseEntity.ok(gestComService.getAllClients());
    }

    /**
     * Récupère un client par son identifiant.
     *
     * @param id l'identifiant du client
     * @return le client correspondant sous forme de DTO
     */
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.getclientById(id));
    }

    /**
     * Supprime un client par son identifiant.
     *
     * @param id l'identifiant du client à supprimer
     * @return un message de confirmation
     */
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.deleteClientById(id));
    }
}
