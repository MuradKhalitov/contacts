package com.example.skillboxthirdtask.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
