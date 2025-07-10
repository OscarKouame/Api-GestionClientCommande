package com.testorangeci.gestcom.controller;

import com.testorangeci.gestcom.dtos.ClientDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.testorangeci.gestcom.dtos.HistoriqueOrdersByClientDTO;
import com.testorangeci.gestcom.dtos.OrderDTO;
import com.testorangeci.gestcom.services.GestComService;

/**
 * Contrôleur REST pour la gestion des commandes.
 * Fournit des endpoints pour la création, la consultation et la suppression des commandes.
 */
@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final GestComService gestComService;

    /**
     * Enregistre une commande pour un client spécifique.
     *
     * @param clientId l'identifiant du client associé
     * @param orderDTO les informations de la commande à créer
     * @return la commande créée au format DTO
     */
    @PostMapping("/clients/{clientId}/orders")
    public ResponseEntity<OrderDTO> saveClient(@PathVariable Long clientId, @Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(gestComService.saveOrder(clientId, orderDTO));
    }

    /**
     * Récupère l'historique des commandes d’un client.
     *
     * @param clientId l'identifiant du client
     * @return les informations du client avec la liste de ses commandes
     */
    @GetMapping("/clients/{clientId}/orders")
    public ResponseEntity<HistoriqueOrdersByClientDTO> getOrderHistoryByClientId(@PathVariable Long clientId){
        return ResponseEntity.ok(gestComService.getOrderHistoryByClientId(clientId));
    }

    /**
     * Récupère le détail d'une commande à partir de son identifiant.
     *
     * @param id l'identifiant de la commande
     * @return la commande trouvée au format DTO
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.getDetailOrderById(id));
    }

    /**
     * Supprime une commande à partir de son identifiant.
     *
     * @param id l'identifiant de la commande à supprimer
     * @return un message de confirmation
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.deleteOrderById(id));
    }


}
