package com.demo.pokepb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.pokepb.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
