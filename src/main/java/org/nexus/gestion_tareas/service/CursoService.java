package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Curso;
import org.nexus.gestion_tareas.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso buscarCursoPorId(Integer codigo) {
        return cursoRepository.findById(codigo).orElse(null);
    }

    @Override
    public void guardarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public void eliminarCurso(Curso curso) {
        cursoRepository.delete(curso);
    }
}
