package com.bilel.SpringBoot_TP01.repos;

import com.bilel.SpringBoot_TP01.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
