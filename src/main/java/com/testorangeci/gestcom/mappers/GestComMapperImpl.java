package com.testorangeci.gestcom.mappers;

import com.testorangeci.gestcom.dtos.ClientDTO;
import com.testorangeci.gestcom.dtos.OrderDTO;
import com.testorangeci.gestcom.entities.Client;
import com.testorangeci.gestcom.entities.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GestComMapperImpl {

    public ClientDTO toClientDTO(Client client) {
        if (client == null) return null;
        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);
        return dto;
    }

    public Client toClient(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        return client;
    }

    public OrderDTO toOrderDTO(Order order) {
        if (order == null) return null;
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(order, dto);
        return dto;
    }

    public Order toOrder(OrderDTO dto) {
        if (dto == null) return null;
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);
        return order;
    }
}
