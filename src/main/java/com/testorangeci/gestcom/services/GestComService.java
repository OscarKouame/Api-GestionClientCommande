package com.testorangeci.gestcom.services;

import java.util.List;
import com.testorangeci.gestcom.dtos.ClientDTO;
import com.testorangeci.gestcom.dtos.HistoriqueOrdersByClientDTO;
import com.testorangeci.gestcom.dtos.OrderDTO;


public interface GestComService {

    ClientDTO saveClient(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    ClientDTO getclientById(Long id);
    String deleteClientById(Long id);
    OrderDTO saveOrder(Long clientId,OrderDTO orderDTO);
    HistoriqueOrdersByClientDTO getOrderHistoryByClientId(Long clientId);
    OrderDTO getDetailOrderById(Long id);
    String deleteOrderById(Long id);
}
