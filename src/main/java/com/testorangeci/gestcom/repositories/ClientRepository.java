package com.testorangeci.gestcom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.testorangeci.gestcom.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
