package org.example.presentacion;

import org.example.business.Estudiante;
import org.example.business.EstudianteServicio;

import java.util.List;
import java.util.Scanner;

public class EstudianteUI {

    private final EstudianteServicio servicio;
    private final Scanner scanner;

    public EstudianteUI(Scanner scanner) {
        this.scanner = scanner;
        this.servicio = new EstudianteServicio();
    }

    public void mostrarMenu() {

        int opcion;

        do {
            limpiarPantalla();

            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║          GESTIÓN DE ESTUDIANTES              ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  [1] Registrar estudiante                    ║");
            System.out.println("║  [2] Listar estudiantes                      ║");
            System.out.println("║  [3] Buscar estudiante                       ║");
            System.out.println("║  [4] Actualizar estudiante                   ║");
            System.out.println("║  [5] Eliminar estudiante                     ║");
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
        System.out.println("           REGISTRAR ESTUDIANTE");
        System.out.println("==============================================");

        int id = leerEntero("ID: ");
        String nombre = leerTexto("Nombre: ");
        String correo = leerTexto("Correo: ");

        Estudiante estudiante = new Estudiante(
                id,
                nombre,
                correo
        );

        boolean registrado = servicio.registrar(estudiante);

        if (registrado) {
            System.out.println("\n[OK] Estudiante registrado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo registrar el estudiante.");
            System.out.println("Verifique el ID, nombre, correo o si el ID ya existe.");
        }

        pausar();
    }

    private void listar() {

        limpiarPantalla();

        System.out.println("==================================================================");
        System.out.println("                      LISTA DE ESTUDIANTES");
        System.out.println("==================================================================");

        List<Estudiante> estudiantes = servicio.listar();

        if (estudiantes.isEmpty()) {
            System.out.println("\nNo hay estudiantes registrados.");
            pausar();
            return;
        }

        System.out.printf(
                "%-6s %-28s %-30s%n",
                "ID",
                "NOMBRE",
                "CORREO"
        );

        System.out.println(
                "------------------------------------------------------------------"
        );

        for (Estudiante estudiante : estudiantes) {
            System.out.printf(
                    "%-6d %-28s %-30s%n",
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getCorreo()
            );
        }

        System.out.println(
                "------------------------------------------------------------------"
        );

        System.out.println(
                "Total de estudiantes: " + estudiantes.size()
        );

        pausar();
    }

    private void buscar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("             BUSCAR ESTUDIANTE");
        System.out.println("==============================================");

        int id = leerEntero("Ingrese el ID del estudiante: ");

        Estudiante estudiante = servicio.buscarPorId(id);

        if (estudiante == null) {
            System.out.println("\n[ERROR] No se encontró un estudiante con ese ID.");
        } else {

            System.out.println("\nEstudiante encontrado");
            System.out.println("----------------------------------------------");
            System.out.println("ID     : " + estudiante.getId());
            System.out.println("Nombre : " + estudiante.getNombre());
            System.out.println("Correo : " + estudiante.getCorreo());
            System.out.println("----------------------------------------------");
        }

        pausar();
    }

    private void actualizar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("           ACTUALIZAR ESTUDIANTE");
        System.out.println("==============================================");

        int id = leerEntero("ID del estudiante: ");

        Estudiante estudianteActual = servicio.buscarPorId(id);

        if (estudianteActual == null) {
            System.out.println("\n[ERROR] Estudiante no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println("Nombre : " + estudianteActual.getNombre());
        System.out.println("Correo : " + estudianteActual.getCorreo());

        System.out.println("\nIngrese los nuevos datos:");

        String nombre = leerTexto("Nuevo nombre: ");
        String correo = leerTexto("Nuevo correo: ");

        Estudiante estudianteActualizado = new Estudiante(
                id,
                nombre,
                correo
        );

        boolean actualizado =
                servicio.actualizar(estudianteActualizado);

        if (actualizado) {
            System.out.println("\n[OK] Estudiante actualizado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo actualizar el estudiante.");
        }

        pausar();
    }

    private void eliminar() {

        limpiarPantalla();

        System.out.println("==============================================");
        System.out.println("            ELIMINAR ESTUDIANTE");
        System.out.println("==============================================");

        int id = leerEntero("ID del estudiante: ");

        Estudiante estudiante = servicio.buscarPorId(id);

        if (estudiante == null) {
            System.out.println("\n[ERROR] Estudiante no encontrado.");
            pausar();
            return;
        }

        System.out.println("\nEstudiante seleccionado:");
        System.out.println(estudiante.getNombre());
        System.out.println(estudiante.getCorreo());

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
            System.out.println("\n[OK] Estudiante eliminado correctamente.");
        } else {
            System.out.println("\n[ERROR] No se pudo eliminar el estudiante.");
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
