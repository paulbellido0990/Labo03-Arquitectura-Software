package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.business.Curso;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositorio {

    private final String archivo = "data/cursos.json";

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public CursoRepositorio() {
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
                    "Error al inicializar el archivo de cursos: "
                            + e.getMessage()
            );
        }
    }

    public List<Curso> listar() {

        try (Reader reader = new FileReader(archivo)) {

            Type tipo = new TypeToken<List<Curso>>() {
            }.getType();

            List<Curso> cursos = gson.fromJson(reader, tipo);

            return cursos != null
                    ? cursos
                    : new ArrayList<>();

        } catch (Exception e) {

            System.out.println(
                    "Error al leer los cursos: "
                            + e.getMessage()
            );

            return new ArrayList<>();
        }
    }

    public void guardar(List<Curso> cursos) {

        try (Writer writer = new FileWriter(archivo)) {

            gson.toJson(cursos, writer);

        } catch (Exception e) {

            System.out.println(
                    "Error al guardar cursos: "
                            + e.getMessage()
            );
        }
    }
}