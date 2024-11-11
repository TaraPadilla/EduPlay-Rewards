package com.proyectoingenieria.project_eduplay_rewards.controller;

import com.proyectoingenieria.project_eduplay_rewards.dto.TutorRegistrationDTO;
import com.proyectoingenieria.project_eduplay_rewards.service.TutorRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/registration")
@Validated
public class RegistrationController {

    @Autowired
    private TutorRegistrationService tutorRegistrationService;

    @PostMapping("/tutor")
    public ResponseEntity<String> registerTutor(@Valid @RequestBody TutorRegistrationDTO tutorRegistrationDTO) {
        try {
            tutorRegistrationService.registerTutor(tutorRegistrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tutor registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el registro del tutor.");
        }
    }
}
