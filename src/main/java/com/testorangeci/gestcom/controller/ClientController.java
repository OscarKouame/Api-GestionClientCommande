package com.testorangeci.gestcom.controller;

import com.testorangeci.gestcom.dtos.ClientDTO;
import com.testorangeci.gestcom.services.GestComService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ClientController {

    private final GestComService gestComService;


    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(gestComService.saveClient(clientDTO));

    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        return ResponseEntity.ok(gestComService.getAllClients());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.getclientById(id));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.deleteClientById(id));
    }




}
