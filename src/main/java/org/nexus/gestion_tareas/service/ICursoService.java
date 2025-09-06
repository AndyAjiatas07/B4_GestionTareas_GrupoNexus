package org.nexus.gestion_tareas.service;
import org.nexus.gestion_tareas.entity.Curso;
import java.util.List;

public interface ICursoService {
    public List<Curso> listarCursos();
    public Curso buscarCursoPorId(Integer codigo);
    public void guardarCurso(Curso curso);
    public void eliminarCurso(Curso curso);
}
