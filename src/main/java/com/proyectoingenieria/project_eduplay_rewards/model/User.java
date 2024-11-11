package com.proyectoingenieria.project_eduplay_rewards.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Integer userId;

    @Column(name = "Full_Name", nullable = false)
    private String fullName;

    @Column(name = "Email_Address", nullable = false, unique = true)
    private String email;

    @Column(name = "Password_Hash", nullable = false)
    private String passwordHash;

    @Column(name = "Registration_Date", nullable = false, updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(name = "Created_At", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "Updated_At")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "Deleted_At")
    private LocalDateTime deletedAt;

    @Column(name = "Is_Active")
    private Boolean isActive = true;
}
