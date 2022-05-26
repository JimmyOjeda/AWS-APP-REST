package com.example.AppRest.controllers;

import com.example.AppRest.models.Profesor;
import com.example.AppRest.services.ProfesorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ControllerProfesor {

    @Autowired
    ProfesorServices professorRepository;

    @GetMapping(path = "/profesores")
    public List<Profesor> getAll() {
        return professorRepository.findAll();
    }

    @GetMapping(path = "/profesores/{id}")
    public ResponseEntity<Profesor> getTeacher(@PathVariable long id) {
        Optional<Profesor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(professorOptional.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProfessor(@RequestBody @Valid Profesor professor) {
        professorRepository.saveAndFlush(professor);
        System.out.println("****************"+professor.getId());
        return new ResponseEntity<>("{\"id\":" + professor.getId() + '}', HttpStatus.CREATED);
    }

    @PutMapping(path = "/profesores/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProfessor(@PathVariable Long id, @RequestBody @Valid Profesor professor) {
        if (professorRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        professor.setId(id);
        professorRepository.save(professor);
        return new ResponseEntity<>("Professor updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "/profesores/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        Optional<Profesor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            return new ResponseEntity<>("Professor: ", HttpStatus.NOT_FOUND);
        }
        professorRepository.delete(professorOptional.get());
        return new ResponseEntity<>("Professor updated", HttpStatus.OK);
    }

}