package com.bilel.SpringBoot_TP01.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long role_id;
    @Column(unique = true)
    private String role;
}
