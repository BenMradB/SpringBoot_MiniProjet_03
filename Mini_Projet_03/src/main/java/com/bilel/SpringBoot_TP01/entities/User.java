package com.bilel.SpringBoot_TP01.entities;

import lombok.Data;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Data
@Entity
 public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean enabled;
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;
}
