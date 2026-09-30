package org.example.business;

import org.example.data.EstudianteRepositorio;

import java.util.List;

public class EstudianteServicio {

    private final EstudianteRepositorio repositorio;

    public EstudianteServicio() {
        this.repositorio = new EstudianteRepositorio();
    }

    public List<Estudiante> listar() {
        return repositorio.listar();
    }

    public Estudiante buscarPorId(int id) {
        return repositorio.listar()
                .stream()
                .filter(estudiante -> estudiante.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean registrar(Estudiante estudiante) {

        if (!datosValidos(estudiante)) {
            return false;
        }

        List<Estudiante> estudiantes = repositorio.listar();

        boolean existeId = estudiantes.stream()
                .anyMatch(e -> e.getId() == estudiante.getId());

        if (existeId) {
            return false;
        }

        estudiantes.add(estudiante);
        repositorio.guardar(estudiantes);

        return true;
    }

    public boolean actualizar(Estudiante estudianteActualizado) {

        if (!datosValidos(estudianteActualizado)) {
            return false;
        }

        List<Estudiante> estudiantes = repositorio.listar();

        for (Estudiante estudiante : estudiantes) {

            if (estudiante.getId() == estudianteActualizado.getId()) {

                estudiante.setNombre(estudianteActualizado.getNombre());
                estudiante.setCorreo(estudianteActualizado.getCorreo());

                repositorio.guardar(estudiantes);

                return true;
            }
        }

        return false;
    }

    public boolean eliminar(int id) {

        List<Estudiante> estudiantes = repositorio.listar();

        boolean eliminado = estudiantes.removeIf(
                estudiante -> estudiante.getId() == id
        );

        if (eliminado) {
            repositorio.guardar(estudiantes);
        }

        return eliminado;
    }

    private boolean datosValidos(Estudiante estudiante) {

        if (estudiante == null) {
            return false;
        }

        if (estudiante.getId() <= 0) {
            return false;
        }

        if (estudiante.getNombre() == null ||
                estudiante.getNombre().isBlank()) {
            return false;
        }

        if (estudiante.getCorreo() == null ||
                estudiante.getCorreo().isBlank()) {
            return false;
        }

        return estudiante.getCorreo().contains("@");
    }
}