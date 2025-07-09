package com.testorangeci.gestcom.dtos;

import java.util.List;
import lombok.Data;

@Data
public class HistoriqueOrdersByClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<OrderDTO> orderDTOList;
}
