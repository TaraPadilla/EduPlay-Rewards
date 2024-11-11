package com.proyectoingenieria.project_eduplay_rewards.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_users")
@Data
@NoArgsConstructor
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Project_User_ID")
    private Integer projectUserId;

    @Column(name = "Project_ID", nullable = false)
    private Integer projectId;

    @Column(name = "User_ID", nullable = false)
    private Integer userId;

    @Column(name = "User_Role", nullable = false)
    private String userRole = "Member";
}
