package com.example.AppRest.services;

import com.example.AppRest.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoServices extends JpaRepository<Alumno, Long> {

}