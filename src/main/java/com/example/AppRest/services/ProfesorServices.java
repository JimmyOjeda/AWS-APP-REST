package com.example.AppRest.services;

import com.example.AppRest.models.Profesor;

import java.util.ArrayList;

public class ProfesorServices {

    private final ArrayList<Profesor> profesores = new ArrayList<>() {{
        add (new Profesor(0,501,"Daniel Jesus","Perez Ayuso",40));
        add (new Profesor(1,502,"Alejandro","Torre Reyes",50));
        add (new Profesor(2,502,"Juan Fernando","Rodriguez Diaz",45));
    }};

    public ArrayList<Profesor> getProfesores () {
        return profesores;
    }

    public Profesor getUnProfesor (long id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId()==(id)) {
                return profesor;
            }
        }
        return null;
    }

    public boolean addProfesor (Profesor profesor) {
        return profesores.add(profesor);
    }

    public boolean deleteProfesor (long id) {
        int index = findIndexProfesor(id);
        if (index > -1) {
            profesores.remove(index);
            return true;
        }
        return false;
    }

    public int findIndexProfesor (long id) {
        int index = -1;
        if (profesores.isEmpty()) return index;
        for (Profesor profesor : profesores) {
            index++;
            if (profesor.getId() == id) {
                return index;
            }
        }
        return -1;
    }

    public boolean updateProfesor (long id, Profesor profesor) {
        int indice = findIndexProfesor(id);
        boolean updated = false;
        if (indice != -1) {
            profesores.get(indice).setNumeroEmpleado(profesor.getNumeroEmpleado());
            profesores.get(indice).setNombres(profesor.getNombres());
            profesores.get(indice).setApellidos(profesor.getApellidos());
            profesores.get(indice).setHorasClase(profesor.getHorasClase());
            updated = true;
        }
        return updated;
    }

}
