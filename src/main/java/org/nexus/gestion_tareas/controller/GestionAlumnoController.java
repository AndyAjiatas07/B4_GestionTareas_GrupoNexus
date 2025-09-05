package org.nexus.gestion_tareas.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.nexus.gestion_tareas.entity.Tarea;
import org.nexus.gestion_tareas.entity.Entrega;
import org.nexus.gestion_tareas.service.ITareaService;
import org.nexus.gestion_tareas.service.IEntregaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class GestionAlumnoController implements Serializable {

    @Autowired
    private ITareaService tareaService;

    @Autowired
    private IEntregaService entregaService;

    private List<Tarea> listaTareas;
    private List<Entrega> listaEntregas;

    private Tarea tareaSeleccionada;
    private Entrega nuevaEntrega;

    @PostConstruct
    public void init() {
        listaTareas = tareaService.listarTareas();
        listaEntregas = entregaService.listarEntregas();
        nuevaEntrega = new Entrega();
        tareaSeleccionada = null; // inicializamos null
    }

    // Seleccionar tarea para entregar
    public void seleccionarTarea(Tarea tarea) {
        this.tareaSeleccionada = tarea;
        nuevaEntrega = new Entrega();
        nuevaEntrega.setTarea(tarea);
    }

    // Entregar tarea
    public void entregarTarea() {
        if (tareaSeleccionada != null && nuevaEntrega != null) {
            nuevaEntrega.setFechaEntrega(LocalDateTime.now());
            nuevaEntrega.setCalificacion(null); // sin calificar aún
            entregaService.guardarEntrega(nuevaEntrega);

            // Actualizar listas
            listaEntregas = entregaService.listarEntregas();
            listaTareas.remove(tareaSeleccionada);

            // Reset
            tareaSeleccionada = null;
            nuevaEntrega = new Entrega();
        }
    }

    // --- Getters y Setters ---
    public List<Tarea> getListaTareas() { return listaTareas; }
    public List<Entrega> getListaEntregas() { return listaEntregas; }

    public Tarea getTareaSeleccionada() { return tareaSeleccionada; }
    public void setTareaSeleccionada(Tarea tareaSeleccionada) { this.tareaSeleccionada = tareaSeleccionada; }

    public Entrega getNuevaEntrega() { return nuevaEntrega; }
    public void setNuevaEntrega(Entrega nuevaEntrega) { this.nuevaEntrega = nuevaEntrega; }
}
