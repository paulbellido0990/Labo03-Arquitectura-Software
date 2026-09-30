package org.example;

import org.example.presentacion.CursoUI;
import org.example.presentacion.EstudianteUI;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EstudianteUI estudianteUI = new EstudianteUI(scanner);
        CursoUI cursoUI = new CursoUI(scanner);

        int opcion;

        do {
            limpiarPantalla();
            mostrarEncabezado();

            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║               MENÚ PRINCIPAL                    ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║                                                  ║");
            System.out.println("║   [1] Gestión de estudiantes                     ║");
            System.out.println("║   [2] Gestión de cursos                          ║");
            System.out.println("║   [0] Salir del sistema                          ║");
            System.out.println("║                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════╝");

            opcion = leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1 -> estudianteUI.mostrarMenu();

                case 2 -> cursoUI.mostrarMenu();

                case 0 -> mostrarDespedida();

                default -> {
                    System.out.println("\n[AVISO] Opción no válida.");
                    pausar();
                }
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarEncabezado() {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║          SISTEMA DE GESTIÓN ACADÉMICA            ║");
        System.out.println("║                                                  ║");
        System.out.println("║        Arquitectura de Software - UNSCH          ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                System.out.println(
                        "[AVISO] Ingrese únicamente valores numéricos."
                );
            }
        }
    }

    private static void mostrarDespedida() {

        limpiarPantalla();

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║              SESIÓN FINALIZADA                   ║");
        System.out.println("║                                                  ║");
        System.out.println("║     Gracias por utilizar el sistema académico    ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
