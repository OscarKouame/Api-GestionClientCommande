package com.testorangeci.gestcom.repositories;

import com.testorangeci.gestcom.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClientId(Long clientId);


}
