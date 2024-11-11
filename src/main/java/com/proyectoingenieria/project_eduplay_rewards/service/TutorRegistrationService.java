package com.proyectoingenieria.project_eduplay_rewards.service;

import com.proyectoingenieria.project_eduplay_rewards.dto.TutorRegistrationDTO;
import com.proyectoingenieria.project_eduplay_rewards.model.User;
import com.proyectoingenieria.project_eduplay_rewards.model.Tutor;
import com.proyectoingenieria.project_eduplay_rewards.model.ProjectUser;
import com.proyectoingenieria.project_eduplay_rewards.repository.UserRepository;
import com.proyectoingenieria.project_eduplay_rewards.repository.TutorRepository;
import com.proyectoingenieria.project_eduplay_rewards.repository.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TutorRegistrationService {

    private static final int EDUPLAY_PROJECT_ID = 1;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private ProjectUserRepository projectUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerTutor(TutorRegistrationDTO dto) throws IllegalArgumentException {
        // Validar que el email no esté en uso
        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // 1. Crear y guardar el usuario en la tabla USERS
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);

        // 2. Crear y guardar el tutor en la tabla TUTOR
        Tutor tutor = new Tutor();
        tutor.setUserId(user.getUserId()); // Asocia el tutor al usuario creado
        tutor.setUserType("Tutor"); // Asigna el tipo de usuario
        tutor = tutorRepository.save(tutor);

        // 3. Crear la asociación en PROJECT_USERS
        ProjectUser projectUser = new ProjectUser();
        projectUser.setProjectId(EDUPLAY_PROJECT_ID);
        projectUser.setUserId(user.getUserId());
        projectUser.setUserRole("Member");
        projectUserRepository.save(projectUser);
    }
}
