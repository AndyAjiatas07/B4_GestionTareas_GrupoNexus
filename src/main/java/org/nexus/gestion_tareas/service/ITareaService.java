package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Tarea;
import java.util.List;

public interface ITareaService {
    public List<Tarea> listarTareas();
    public Tarea buscarTareaPorId(Integer codigo);
    public void guardarTarea(Tarea tarea);
    public void eliminarTarea(Tarea tarea);
}
