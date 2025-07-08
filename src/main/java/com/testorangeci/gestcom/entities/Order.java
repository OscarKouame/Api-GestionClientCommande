package com.testorangeci.gestcom.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double amount;
    private Date createdAt;
    @ManyToOne
    private Client client;
}
