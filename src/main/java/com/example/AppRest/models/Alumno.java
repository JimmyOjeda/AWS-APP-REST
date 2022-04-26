package com.example.AppRest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Alumno {

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

}
