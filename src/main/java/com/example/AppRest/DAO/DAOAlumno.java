package com.example.AppRest.DAO;

import com.example.AppRest.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DAOAlumno extends JpaRepository<Alumno,Long> {


}
