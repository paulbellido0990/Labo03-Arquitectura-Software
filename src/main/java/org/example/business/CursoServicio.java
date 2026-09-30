package org.example.business;

import org.example.data.CursoRepositorio;

import java.util.List;

public class CursoServicio {

    private final CursoRepositorio repositorio;

    public CursoServicio() {
        this.repositorio = new CursoRepositorio();
    }

    public List<Curso> listar() {
        return repositorio.listar();
    }

    public Curso buscarPorId(int id) {
        return repositorio.listar()
                .stream()
                .filter(curso -> curso.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean registrar(Curso curso) {

        if (!datosValidos(curso)) {
            return false;
        }

        List<Curso> cursos = repositorio.listar();

        boolean existeId = cursos.stream()
                .anyMatch(c -> c.getId() == curso.getId());

        if (existeId) {
            return false;
        }

        cursos.add(curso);
        repositorio.guardar(cursos);

        return true;
    }

    public boolean actualizar(Curso cursoActualizado) {

        if (!datosValidos(cursoActualizado)) {
            return false;
        }

        List<Curso> cursos = repositorio.listar();

        for (Curso curso : cursos) {

            if (curso.getId() == cursoActualizado.getId()) {

                curso.setNombre(cursoActualizado.getNombre());
                curso.setCreditos(cursoActualizado.getCreditos());
                curso.setDocente(cursoActualizado.getDocente());

                repositorio.guardar(cursos);

                return true;
            }
        }

        return false;
    }

    public boolean eliminar(int id) {

        List<Curso> cursos = repositorio.listar();

        boolean eliminado = cursos.removeIf(
                curso -> curso.getId() == id
        );

        if (eliminado) {
            repositorio.guardar(cursos);
        }

        return eliminado;
    }

    private boolean datosValidos(Curso curso) {

        if (curso == null) {
            return false;
        }

        if (curso.getId() <= 0) {
            return false;
        }

        if (curso.getNombre() == null ||
                curso.getNombre().isBlank()) {
            return false;
        }

        if (curso.getCreditos() <= 0) {
            return false;
        }

        return curso.getDocente() != null &&
                !curso.getDocente().isBlank();
    }
}