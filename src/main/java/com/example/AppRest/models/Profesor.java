package com.example.AppRest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity @Table(name = "profesor") @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
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

    @Override
    public String toString() {
        return "Professor{id:" + id + ", numeroEmpleado:" + numeroEmpleado + ", nombres:'" + nombres + '\'' + ", apellidos:'" + apellidos + '\'' + ", horasClase:'" + horasClase + '\'' + '}';
    }

}
