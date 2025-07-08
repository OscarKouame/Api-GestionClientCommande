package com.testorangeci.gestcom.services;

import com.testorangeci.gestcom.Exceptions.ClientNotFoundExeption;
import com.testorangeci.gestcom.Exceptions.OrderNotFoundExeption;
import com.testorangeci.gestcom.dtos.ClientDTO;
import com.testorangeci.gestcom.dtos.HistoriqueOrdersByClientDTO;
import com.testorangeci.gestcom.dtos.OrderDTO;
import com.testorangeci.gestcom.entities.Client;
import com.testorangeci.gestcom.entities.Order;
import com.testorangeci.gestcom.mappers.GestComMapperImpl;
import com.testorangeci.gestcom.repositories.ClientRepository;
import com.testorangeci.gestcom.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service @AllArgsConstructor @Slf4j
public class GestComServiceImpl implements GestComService{

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final GestComMapperImpl gestComMapper;

    // Cette methode enregistre un nouveau client à partir d'un DTO.
    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("Ajout du client : {}", clientDTO);
        Client client = gestComMapper.toClient(clientDTO);
        client.setCreateAt(new Date());
        Client savedClient = clientRepository.save(client);
        return gestComMapper.toClientDTO(savedClient);
    }

    // Cette methode récupère tous les clients enregistrés..
    @Override
    public List<ClientDTO> getAllClients() {
        log.info("Récupération de la liste de tous les clients");
        return clientRepository.findAll()
                .stream()
                .map(gestComMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    // Cette methode  récupère un client à partir de son ID.
    @Override
    public ClientDTO getclientById(Long id) {
        log.info("Detail du client {}", id);
        Client client = clientRepository.findById(id).orElseThrow(()->new ClientNotFoundExeption("Ce client est introuvable"));
        return gestComMapper.toClientDTO(client);
    }

    // Cette methode supprime un client par son ID.
    @Override
    public String deleteClientById(Long id) {
        log.info("Suppression du client {}", id);
        Client client = clientRepository.findById(id).orElseThrow(()->new ClientNotFoundExeption("Ce client est introuvable"));
        clientRepository.delete(client);
        return "Client supprimé avec succes";
    }

    // Cette methode enregistre une commande associée à un client donné.
    @Override
    public OrderDTO saveOrder(Long clientId, OrderDTO orderDTO) {
        log.info("Ajout d'une commande pour le client {} : {}", clientId, orderDTO);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundExeption("Ce client est introuvable"));
        Order order = gestComMapper.toOrder(orderDTO);
        order.setClient(client);
        order.setCreatedAt(new Date());
        Order savedOrder = orderRepository.save(order);
        return gestComMapper.toOrderDTO(savedOrder);
    }

    // Cette methode Récupère l’historique des commandes d’un client donné.
    @Override
    public HistoriqueOrdersByClientDTO getOrderHistoryByClientId(Long clientId) {
        log.info("Récupération des commandes du client : {}", clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundExeption("Ce client est introuvable"));
        List<OrderDTO> orderDTOList = orderRepository.findByClientId(clientId).stream()
                .map(gestComMapper::toOrderDTO)
                .collect(Collectors.toList());

        HistoriqueOrdersByClientDTO historique = new HistoriqueOrdersByClientDTO();
        historique.setId(client.getId());
        historique.setFirstName(client.getFirstName());
        historique.setLastName(client.getLastName());
        historique.setOrderDTOList(orderDTOList);
        return historique;
    }

    // Cette methode récupère le détail d’une commande par son identifiant.
    @Override
    public OrderDTO getDetailOrderById(Long id) {
        log.info("Récupération des détails de la commande : {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundExeption("Cette commande est introuvable"));
        return gestComMapper.toOrderDTO(order);
    }

    // Cette methode supprime une commande à partir de son ID.
    public String deleteOrderById(Long id) {
        log.info("Suppression de la commande : {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundExeption("Cette commande est introuvable"));
        orderRepository.delete(order);
        return "Commande supprimée avec succes";
    }
}
