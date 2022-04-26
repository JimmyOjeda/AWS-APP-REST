package com.example.AppRest.controllers;

import com.example.AppRest.models.Profesor;
import com.example.AppRest.services.ProfesorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ControllerProfesor {

    private ProfesorServices profesorServices = new ProfesorServices();

    @GetMapping("/profesores")
    public ResponseEntity GETProfesores () {
        return new ResponseEntity(profesorServices.getProfesores(), HttpStatus.OK);
    }

    @GetMapping(path = "/profesores/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable long id) {
        Profesor profesor = profesorServices.getUnProfesor(id);
        if (profesor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profesor, HttpStatus.OK);
    }

    @PostMapping(path = "/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity POSTProfesores (@RequestBody @Valid Profesor profesor) {
        ResponseEntity response;
        boolean status;

        try {
            status = profesorServices.addProfesor(profesor);
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

    @PutMapping(path = "/profesores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity PUTProfesor (@PathVariable int id, @Valid @RequestBody Profesor profesor) {
        if (profesorServices.updateProfesor(id,profesor)){
            return new ResponseEntity("Actualizado con exito",HttpStatus.OK);
        }
        return new ResponseEntity("No se ha actualizado",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/profesores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> DELETEProfesor (@PathVariable long id) {
        ResponseEntity response;

        if (profesorServices.deleteProfesor(id)){
            response = new ResponseEntity("Eliminado correctamente",HttpStatus.OK);
        }else{
            response = new ResponseEntity("No se encontró el alumno",HttpStatus.NOT_FOUND);
        }

        return response;
    }

}
