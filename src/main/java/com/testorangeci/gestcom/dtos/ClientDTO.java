package com.testorangeci.gestcom.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ClientDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String firstName;
    @NotBlank(message = "Le prenom est obligatoire")
    private String lastName;
    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;
}
