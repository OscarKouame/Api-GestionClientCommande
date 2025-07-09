package com.testorangeci.gestcom.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.testorangeci.gestcom.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClientId(Long clientId);


}
