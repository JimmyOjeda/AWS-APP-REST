package com.example.AppRest.services;

import com.example.AppRest.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorServices extends JpaRepository<Profesor, Long> {
}