package org.example.data;
import org.example.business.Estudiante;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class EstudianteRepositorio {
    private final String archivo="data/estudiantes.json";
    private final Gson gson=new Gson();


    public List <Estudiante> listar(){
        try(Reader reader=new FileReader(archivo)){
            Type tipo=new TypeToken <List <Estudiante>>() {}.getType();
            List<Estudiante> estudiantes=gson.fromJson(reader,tipo);
            return estudiantes!=null? estudiantes: new ArrayList<>();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public void guardar(List<Estudiante> estudiantes){
        try (Writer writer=new FileWriter(archivo)){
            gson.toJson(estudiantes,writer);
        } catch (Exception e) {
            System.out.println("Error al guardar estudiante");
        }
    }
}
