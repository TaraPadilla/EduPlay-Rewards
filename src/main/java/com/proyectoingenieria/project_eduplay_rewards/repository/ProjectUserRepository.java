package com.proyectoingenieria.project_eduplay_rewards.repository;

import com.proyectoingenieria.project_eduplay_rewards.model.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
}
