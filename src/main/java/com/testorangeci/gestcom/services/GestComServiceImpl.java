package com.testorangeci.gestcom.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

/**
 * Implémentation du service de gestion des clients et des commandes.
 * Fournit les opérations CRUD sur les entités Client et Order.
 */
@Service
@AllArgsConstructor
@Slf4j
public class GestComServiceImpl implements GestComService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final GestComMapperImpl gestComMapper;

    /**
     * Enregistre un nouveau client à partir d'un DTO.
     *
     * @param clientDTO les informations du client à sauvegarder
     * @return le client enregistré sous forme de DTO
     */
    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("Ajout du client : {}", clientDTO);
        Client client = gestComMapper.toClient(clientDTO);
        client.setCreateAt(LocalDateTime.now());
        Client savedClient = clientRepository.save(client);
        return gestComMapper.toClientDTO(savedClient);
    }

    /**
     * Récupère la liste de tous les clients enregistrés.
     *
     * @return une liste de clients au format DTO
     */
    @Override
    public List<ClientDTO> getAllClients() {
        log.info("Récupération de la liste de tous les clients");
        return clientRepository.findAll()
                .stream()
                .map(gestComMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un client à partir de son identifiant.
     *
     * @param id l'identifiant du client
     * @return le client trouvé sous forme de DTO
     * @throws ClientNotFoundExeption si le client n'existe pas
     */
    @Override
    public ClientDTO getclientById(Long id) {
        log.info("Détail du client {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundExeption("Ce client est introuvable"));
        return gestComMapper.toClientDTO(client);
    }

    /**
     * Supprime un client à partir de son identifiant.
     *
     * @param id l'identifiant du client à supprimer
     * @return un message de confirmation
     * @throws ClientNotFoundExeption si le client n'existe pas
     */
    @Override
    public String deleteClientById(Long id) {
        log.info("Suppression du client {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundExeption("Ce client est introuvable"));
        clientRepository.delete(client);
        return "Client supprimé avec succès";
    }

    /**
     * Enregistre une commande pour un client donné.
     *
     * @param clientId l'identifiant du client
     * @param orderDTO les détails de la commande
     * @return la commande enregistrée
     * @throws ClientNotFoundExeption si le client n'existe pas
     */
    @Override
    public OrderDTO saveOrder(Long clientId, OrderDTO orderDTO) {
        log.info("Ajout d'une commande pour le client {} : {}", clientId, orderDTO);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundExeption("Ce client est introuvable"));
        Order order = gestComMapper.toOrder(orderDTO);
        order.setClient(client);
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return gestComMapper.toOrderDTO(savedOrder);
    }

    /**
     * Récupère l’historique des commandes d’un client donné.
     *
     * @param clientId l'identifiant du client
     * @return l'historique des commandes avec les infos du client
     * @throws ClientNotFoundExeption si le client n'existe pas
     */
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

    /**
     * Récupère le détail d'une commande à partir de son identifiant.
     *
     * @param id l'identifiant de la commande
     * @return la commande au format DTO
     * @throws OrderNotFoundExeption si la commande n'existe pas
     */
    @Override
    public OrderDTO getDetailOrderById(Long id) {
        log.info("Récupération des détails de la commande : {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundExeption("Cette commande est introuvable"));
        return gestComMapper.toOrderDTO(order);
    }

    /**
     * Supprime une commande à partir de son identifiant.
     *
     * @param id l'identifiant de la commande
     * @return un message de confirmation
     * @throws OrderNotFoundExeption si la commande n'existe pas
     */
    public String deleteOrderById(Long id) {
        log.info("Suppression de la commande : {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundExeption("Cette commande est introuvable"));
        orderRepository.delete(order);
        return "Commande supprimée avec succès";
    }
}
