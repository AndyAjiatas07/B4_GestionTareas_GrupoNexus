package org.nexus.gestion_tareas.repository;

import org.nexus.gestion_tareas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer>  {
}
