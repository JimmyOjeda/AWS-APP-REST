package com.example.AppRest.controllers;

import com.example.AppRest.models.Alumno;
import com.example.AppRest.services.AlumnoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ControllerAlumno {

    @Autowired
    private AlumnoServices alumnoServices = new AlumnoServices();

    @GetMapping("/alumnos")
    public ResponseEntity GETAlumnos () {
        return new ResponseEntity(alumnoServices.getAlumnos(),HttpStatus.OK);
    }

    @GetMapping(path = "/alumnos/{id}")
    public ResponseEntity<Alumno> getAlumno (@PathVariable long id) {
        Alumno alumno = alumnoServices.getUnAlumno(id);
        if (alumno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @PostMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity POSTAlumnos (@RequestBody @Valid Alumno alumno) {
        ResponseEntity response;
        boolean status;

        try {
            status = alumnoServices.addAlumno(alumno);
        }catch (Exception e) {
            status = false;
        }


        if (status) {
            response = new ResponseEntity("Creado con éxito", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity("Error al crear", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping(path = "/alumnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity PUTAlumnos (@PathVariable int id, @Valid @RequestBody Alumno alumno) {
        if (alumnoServices.updateAlumno(id,alumno)){
            return new ResponseEntity("Actualizado con exito",HttpStatus.OK);
        }
        return new ResponseEntity("No se ha actualizado",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/alumnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> DELETEAlumno (@PathVariable long id) {
        ResponseEntity response;

        if (alumnoServices.deleteAlumno(id)){
            response = new ResponseEntity("Eliminado correctamente",HttpStatus.OK);
        }else{
            response = new ResponseEntity("No se encontró el alumno",HttpStatus.NOT_FOUND);
        }

        return response;
    }



}
