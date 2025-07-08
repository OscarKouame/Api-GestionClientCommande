package com.testorangeci.gestcom.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
 @Entity
 @Data @NoArgsConstructor @AllArgsConstructor
public class Client {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date createAt;
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
