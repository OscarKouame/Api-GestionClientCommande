package com.testorangeci.gestcom.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {

    @NotNull(message = "Le montant est requis")
    private String description;
    @NotNull(message = "Le montant est requis")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private double amount;
}
