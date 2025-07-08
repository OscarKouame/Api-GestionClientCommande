package com.testorangeci.gestcom.controller;


import com.testorangeci.gestcom.dtos.HistoriqueOrdersByClientDTO;
import com.testorangeci.gestcom.dtos.OrderDTO;
import com.testorangeci.gestcom.services.GestComService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final GestComService gestComService;

    @PostMapping("/clients/{clientId}/orders")
    public ResponseEntity<OrderDTO> saveClient(@PathVariable Long clientId, @Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(gestComService.saveOrder(clientId,orderDTO));
    }
    
    @GetMapping("/clients/{clientId}/orders")
    public ResponseEntity<HistoriqueOrdersByClientDTO> getOrderHistoryByClientId(@PathVariable Long clientId){
        return ResponseEntity.ok(gestComService.getOrderHistoryByClientId(clientId));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.getDetailOrderById(id));
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id){
        return ResponseEntity.ok(gestComService.deleteOrderById(id));
    }
    
}
