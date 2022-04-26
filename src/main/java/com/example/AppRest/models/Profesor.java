package com.example.AppRest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Profesor {

    @NotNull
    @PositiveOrZero
    private long id;

    @NotNull
    @PositiveOrZero
    private int numeroEmpleado;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    @NotNull
    @PositiveOrZero
    private int horasClase;

}
