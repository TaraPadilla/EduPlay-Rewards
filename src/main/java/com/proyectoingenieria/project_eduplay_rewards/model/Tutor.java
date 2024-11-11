package com.proyectoingenieria.project_eduplay_rewards.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tutor")
@Data
@NoArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tutor_ID")
    private Integer tutorId;

    @Column(name = "User_ID", nullable = false)
    private Integer userId;

    @Column(name = "User_Type")
    private String userType;

    @Column(name = "Created_At", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "Updated_At")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "Deleted_At")
    private LocalDateTime deletedAt;

    @Column(name = "Is_Active")
    private Boolean isActive = true;
}
