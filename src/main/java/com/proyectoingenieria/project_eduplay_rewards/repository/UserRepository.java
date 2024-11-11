package com.proyectoingenieria.project_eduplay_rewards.repository;

import com.proyectoingenieria.project_eduplay_rewards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email); // Para buscar usuarios por email
}
