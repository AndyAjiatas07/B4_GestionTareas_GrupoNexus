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

    @Autowired
    private ITareaService tareaService;

    @Autowired
    private IEntregaService entregaService;

    private List<Tarea> listaTareas;
    private List<Entrega> listaEntregas;

    private Tarea nuevaTarea;
    private Tarea tareaSeleccionada;
    private Tarea tareaEnEdicion;
    private Entrega entregaSeleccionada;

    @PostConstruct
    public void init() {
        // Listas
        listaTareas = tareaService.listarTareas();
        listaEntregas = entregaService.listarEntregas();

        // Variables de tarea
        nuevaTarea = new Tarea();
        tareaSeleccionada = new Tarea();
        tareaEnEdicion = new Tarea();

        // Variable de entrega
        entregaSeleccionada = new Entrega();
    }

    // --- Tareas ---
    public void crearTarea() {
        nuevaTarea.setFechaEntrega(LocalDateTime.now());
        tareaService.guardarTarea(nuevaTarea);
        listaTareas = tareaService.listarTareas();
        nuevaTarea = new Tarea();
    }

    public void prepararEdicion(Tarea tarea) {
        if (tarea != null) {
            this.tareaEnEdicion = tarea;
        } else {
            this.tareaEnEdicion = new Tarea();
        }
    }

    public void actualizarTarea() {
        if (tareaEnEdicion != null) {
            tareaService.guardarTarea(tareaEnEdicion);
            listaTareas = tareaService.listarTareas();
        }
    }

    public void seleccionarTarea(Tarea tarea) {
        this.tareaSeleccionada = tarea;
    }

    public void eliminarTarea(Tarea tarea) {
        tareaService.eliminarTarea(tarea);
        listaTareas = tareaService.listarTareas();
    }

    // --- Entregas ---
    public void calificarEntrega() {
        for (Entrega entrega : listaEntregas) {
            entregaService.guardarEntrega(entrega);
        }
        listaEntregas = entregaService.listarEntregas();
    }

    // --- Getters y Setters ---
    public List<Tarea> getListaTareas() { return listaTareas; }
    public List<Entrega> getListaEntregas() { return listaEntregas; }

    public Tarea getNuevaTarea() { return nuevaTarea; }
    public void setNuevaTarea(Tarea nuevaTarea) { this.nuevaTarea = nuevaTarea; }

    public Tarea getTareaSeleccionada() { return tareaSeleccionada; }
    public void setTareaSeleccionada(Tarea tareaSeleccionada) { this.tareaSeleccionada = tareaSeleccionada; }

    public Tarea getTareaEnEdicion() { return tareaEnEdicion; }
    public void setTareaEnEdicion(Tarea tareaEnEdicion) { this.tareaEnEdicion = tareaEnEdicion; }

    public Entrega getEntregaSeleccionada() { return entregaSeleccionada; }
    public void setEntregaSeleccionada(Entrega entregaSeleccionada) { this.entregaSeleccionada = entregaSeleccionada; }
}

