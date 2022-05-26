package com.example.AppRest.controllers;

import com.example.AppRest.models.Alumno;
import com.example.AppRest.services.AlumnoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ControllerAlumno {

    @Autowired
    AlumnoServices studentRepository;

    @Autowired
    private S3Controller s3Service;

    @GetMapping(path = "/alumnos")
    public List<Alumno> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/alumnos/{id}")
    public ResponseEntity<Alumno> getStudent(@PathVariable long id) {
        Optional<Alumno> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudent(@RequestBody @Valid Alumno student) {
        studentRepository.saveAndFlush(student);
        return new ResponseEntity<>("{\"id\":" + student.getId() + '}', HttpStatus.CREATED);
    }

    @PutMapping(path = "/alumnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @Valid @RequestBody Alumno student) {
        if (studentRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(id);
        studentRepository.save(student);
        return new ResponseEntity<>("Student updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "/alumnos/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        Optional<Alumno> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.delete(studentOptional.get());
        return new ResponseEntity<>("Professor updated", HttpStatus.OK);
    }

    @PostMapping(path = "/alumnos/{id}/fotoPerfil", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> addStudentAndPhoto(@PathVariable long id, @RequestPart(value="foto") MultipartFile file){

        s3Service.uploadFile(id,file);
        String URLfromS3 = s3Service.getLinkFromS3(id,file.getOriginalFilename());

        Alumno student = studentRepository.getById(id);
        student.setFotoPerfilUrl(URLfromS3);
        studentRepository.save(student);

        HashMap<String,String>Json = new HashMap<>();
        Json.put("fotoPerfilUrl",student.getFotoPerfilUrl());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(Json);
    }

}