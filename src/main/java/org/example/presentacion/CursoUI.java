package org.example.presentacion;

import org.example.business.Curso;
import org.example.business.CursoServicio;

import java.util.List;
import java.util.Scanner;

public class CursoUI {

    private final CursoServicio servicio;
    private final Scanner scanner;

    public CursoUI(Scanner scanner) {
        this.scanner = scanner;
        this.servicio = new CursoServicio();
    }

    public void mostrarMenu() {

        int opcion;

        do {
            limpiarPantalla();

            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              GESTIÓN DE CURSOS               ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  [1] Registrar curso                         ║");
            System.out.println("║  [2] Listar cursos                           ║");
            System.out.println("║  [3] Buscar curso                            ║");
            System.out.println("║  [4] Actualizar curso                        ║");
            System.out.println("║  [5] Eliminar curso                          ║");
            System.out.println("║  [0] Volver al menú principal                ║");
            System.out.println("╚══════════════════════════════════════════════╝");

            opcion = leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 0 -> System.out.println("\nRegresando al menú principal...");
                default -> {
                    System.out.println("\nOpción no válida.");
                    pausar();
                }
            }

        } while (opcion != 0);
    }

    private void registrar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("              REGISTRAR CURSO");
        System.out.println("==============================================");

        int id = leerEntero("ID: ");
        String nombre = leerTexto("Nombre del curso: ");
        int creditos = leerEntero("Créditos: ");
        String docente = leerTexto("Docente: ");

        Curso curso = new Curso(
                id,
                nombre,
                creditos,
                docente
        );

        boolean registrado = servicio.registrar(curso);

        if (registrado) {
            System.out.println("\n[OK] Curso registrado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo registrar el curso.");
            System.out.println("Verifique el ID, nombre, créditos o docente.");
        }

        pausar();
    }

    private void listar() {

        limpiarPantalla();

        System.out.println("================================================================================");
        System.out.println("                              LISTA DE CURSOS");
        System.out.println("================================================================================");

        List<Curso> cursos = servicio.listar();

        if (cursos.isEmpty()) {
            System.out.println("\nNo hay cursos registrados.");
            pausar();
            return;
        }

        System.out.printf(
                "%-6s %-28s %-10s %-28s%n",
                "ID",
                "CURSO",
                "CRÉDITOS",
                "DOCENTE"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (Curso curso : cursos) {
            System.out.printf(
                    "%-6d %-28s %-10d %-28s%n",
                    curso.getId(),
                    curso.getNombre(),
                    curso.getCreditos(),
                    curso.getDocente()
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        System.out.println(
                "Total de cursos: " + cursos.size()
        );

        pausar();
    }

    private void buscar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("                BUSCAR CURSO");
        System.out.println("==============================================");

        int id = leerEntero("Ingrese el ID del curso: ");

        Curso curso = servicio.buscarPorId(id);

        if (curso == null) {
            System.out.println("\n[ERROR] No se encontró un curso con ese ID.");
        } else {

            System.out.println("\nCurso encontrado");
            System.out.println("----------------------------------------------");
            System.out.println("ID       : " + curso.getId());
            System.out.println("Nombre   : " + curso.getNombre());
            System.out.println("Créditos : " + curso.getCreditos());
            System.out.println("Docente  : " + curso.getDocente());
            System.out.println("----------------------------------------------");
        }

        pausar();
    }

    private void actualizar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("              ACTUALIZAR CURSO");
        System.out.println("==============================================");

        int id = leerEntero("ID del curso: ");

        Curso cursoActual = servicio.buscarPorId(id);

        if (cursoActual == null) {
            System.out.println("\n[ERROR] Curso no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println("Nombre   : " + cursoActual.getNombre());
        System.out.println("Créditos : " + cursoActual.getCreditos());
        System.out.println("Docente  : " + cursoActual.getDocente());

        System.out.println("\nIngrese los nuevos datos:");

        String nombre = leerTexto("Nuevo nombre: ");
        int creditos = leerEntero("Nuevos créditos: ");
        String docente = leerTexto("Nuevo docente: ");

        Curso cursoActualizado = new Curso(
                id,
                nombre,
                creditos,
                docente
        );

        boolean actualizado =
                servicio.actualizar(cursoActualizado);

        if (actualizado) {
            System.out.println("\n[OK] Curso actualizado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo actualizar el curso.");
        }

        pausar();
    }

    private void eliminar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("               ELIMINAR CURSO");
        System.out.println("==============================================");

        int id = leerEntero("ID del curso: ");

        Curso curso = servicio.buscarPorId(id);

        if (curso == null) {
            System.out.println("\n[ERROR] Curso no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nCurso seleccionado:");
        System.out.println("Nombre  : " + curso.getNombre());
        System.out.println("Docente : " + curso.getDocente());

        String confirmacion = leerTexto(
                "\n¿Está seguro de eliminarlo? (S/N): "
        );

        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println("\nOperación cancelada.");
            pausar();
            return;
        }

        boolean eliminado = servicio.eliminar(id);

        if (eliminado) {
            System.out.println("\n[OK] Curso eliminado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo eliminar el curso.");
        }

        pausar();
    }

    private int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                System.out.println(
                        "[AVISO] Ingrese un número válido."
                );
            }
        }
    }

    private String leerTexto(String mensaje) {

        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(
                        "[AVISO] Este campo no puede estar vacío."
                );
            }

        } while (texto.isEmpty());

        return texto;
    }

    private void pausar() {
        System.out.println(
                "\nPresione ENTER para continuar..."
        );
        scanner.nextLine();
    }

    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}