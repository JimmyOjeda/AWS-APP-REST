package com.example.AppRest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity @Table(name = "alumno") @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    @NotNull
    @PositiveOrZero
    private long id;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    @NotEmpty
    private String matricula;

    @NotNull
    @PositiveOrZero
    private double promedio;

    private String fotoPerfilUrl;
}
