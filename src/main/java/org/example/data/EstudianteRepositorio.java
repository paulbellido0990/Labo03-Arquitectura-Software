package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.business.Estudiante;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositorio {

    private final String archivo = "data/estudiantes.json";

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public EstudianteRepositorio() {
        inicializarArchivo();
    }

    private void inicializarArchivo() {
        try {
            File archivoJson = new File(archivo);

            File carpeta = archivoJson.getParentFile();

            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }

            if (!archivoJson.exists()) {
                try (Writer writer = new FileWriter(archivoJson)) {
                    writer.write("[]");
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al inicializar el archivo de estudiantes: "
                            + e.getMessage()
            );
        }
    }

    public List<Estudiante> listar() {

        try (Reader reader = new FileReader(archivo)) {

            Type tipo = new TypeToken<List<Estudiante>>() {
            }.getType();

            List<Estudiante> estudiantes = gson.fromJson(reader, tipo);

            return estudiantes != null
                    ? estudiantes
                    : new ArrayList<>();

        } catch (Exception e) {

            System.out.println(
                    "Error al leer los estudiantes: "
                            + e.getMessage()
            );

            return new ArrayList<>();
        }
    }

    public void guardar(List<Estudiante> estudiantes) {

        try (Writer writer = new FileWriter(archivo)) {

            gson.toJson(estudiantes, writer);

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar estudiantes: "
                            + e.getMessage()
            );
        }
    }
}