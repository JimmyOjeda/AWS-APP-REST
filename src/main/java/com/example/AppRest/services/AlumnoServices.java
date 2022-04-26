package com.example.AppRest.services;

import com.example.AppRest.models.Alumno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlumnoServices {

    private final ArrayList<Alumno> alumnos = new ArrayList<>() {{
        add (new Alumno(0,"Daniel Jesus","Perez Ayuso","15001888",98.5));
        add (new Alumno(1,"Alejandro","Torre Reyes","150018889",99.8));
        add (new Alumno(2,"Juan Fernando","Rodriguez Diaz","15001890",94.5));
    }};

    public ArrayList<Alumno> getAlumnos () {
        return alumnos;
    }

    public Alumno getUnAlumno (long id) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId()==(id)) {
                return alumno;
            }
        }
        return null;
    }

    public boolean addAlumno (Alumno alumno) {
        return alumnos.add(alumno);
    }

    public boolean deleteAlumno (long id) {
        int index = findIndexAlumno(id);
        if (index > -1) {
            alumnos.remove(index);
            return true;
        }
        return false;
    }

    public int findIndexAlumno (long id) {
        int index = -1;
        if (alumnos.isEmpty()) return index;
        for (Alumno alumno : alumnos) {
            index++;
            if (alumno.getId() == id) {
                return index;
            }
        }
        return -1;
    }

    public boolean updateAlumno (int id, Alumno alumno) {
        int indice = findIndexAlumno(id);
        boolean updated = false;
        if (indice != -1) {
            alumnos.get(indice).setMatricula(alumno.getMatricula());
            alumnos.get(indice).setNombres(alumno.getNombres());
            alumnos.get(indice).setApellidos(alumno.getApellidos());
            alumnos.get(indice).setPromedio(alumno.getPromedio());
            updated = true;
        }
        return updated;
    }

}
