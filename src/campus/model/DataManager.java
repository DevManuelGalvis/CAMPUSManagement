package campus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton para guardar colaboradores y estudiantes en memoria.
 */
public class DataManager {
    private static DataManager instance;
    private final List<Colaborador> colaboradores;
    private final List<Estudiante> estudiantes;

    private DataManager() {
        colaboradores = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) instance = new DataManager();
        return instance;
    }

    public void addColaborador(Colaborador c) { colaboradores.add(c); }
    public void addEstudiante(Estudiante e) { estudiantes.add(e); }

    public List<Colaborador> getColaboradores() { return new ArrayList<>(colaboradores); }
    public List<Estudiante> getEstudiantes() { return new ArrayList<>(estudiantes); }

    public String getColaboradoresAsString() {
        if (colaboradores.isEmpty()) return "(ninguno)";
        StringBuilder sb = new StringBuilder();
        for (Colaborador c : colaboradores) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getEstudiantesAsString() {
        if (estudiantes.isEmpty()) return "(ninguno)";
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : estudiantes) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
