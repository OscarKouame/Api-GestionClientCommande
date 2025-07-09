package com.testorangeci.gestcom.entities;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


 @Entity
 @Data @NoArgsConstructor @AllArgsConstructor
public class Client {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createAt;
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
