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
public class GestionTareasController implements Serializable {

    @Autowired
    private ITareaService tareaService;

    @Autowired
    private IEntregaService entregaService;

    private List<Tarea> listaTareas;
    private List<Entrega> listaEntregas;
    private Tarea nuevaTarea = new Tarea();
    private Entrega entregaSeleccionada;

    @PostConstruct
    public void init() {
        listaTareas = tareaService.listarTareas();
        listaEntregas = entregaService.listarEntregas();
    }

    public void crearTarea() {
        nuevaTarea.setFechaEntrega(LocalDateTime.now()); // ejemplo temporal, ajustar según input
        tareaService.guardarTarea(nuevaTarea);
        listaTareas = tareaService.listarTareas();
        nuevaTarea = new Tarea();
    }

    public void calificarEntrega() {
        if (entregaSeleccionada != null) {
            entregaService.guardarEntrega(entregaSeleccionada);
            listaEntregas = entregaService.listarEntregas();
        }
    }

    // Getters y Setters
    public List<Tarea> getListaTareas() { return listaTareas; }
    public List<Entrega> getListaEntregas() { return listaEntregas; }
    public Tarea getNuevaTarea() { return nuevaTarea; }
    public void setNuevaTarea(Tarea nuevaTarea) { this.nuevaTarea = nuevaTarea; }
    public Entrega getEntregaSeleccionada() { return entregaSeleccionada; }
    public void setEntregaSeleccionada(Entrega entregaSeleccionada) { this.entregaSeleccionada = entregaSeleccionada; }
}
