package com.testorangeci.gestcom.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoriqueOrdersByClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<OrderDTO> orderDTOList;
}
