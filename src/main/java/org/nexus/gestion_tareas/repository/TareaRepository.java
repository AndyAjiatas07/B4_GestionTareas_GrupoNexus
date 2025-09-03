package org.nexus.gestion_tareas.repository;

import org.nexus.gestion_tareas.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

}
