package com.example.crudsimpleapi.repository;

import com.example.crudsimpleapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
