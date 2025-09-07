package org.nexus.gestion_tareas.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.nexus.gestion_tareas.entity.Curso;
import org.nexus.gestion_tareas.entity.Usuario;
import org.nexus.gestion_tareas.service.ICursoService;
import org.primefaces.PrimeFaces;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Named("cursoController")
@ViewScoped
@Data
public class CursoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ICursoService cursoService;

    private List<Curso> cursos;
    private Curso cursoSeleccionado;

    @PostConstruct
    public void init() {
        cargarCursos();
    }

    public void cargarCursos() {
        this.cursos = cursoService.listarCursos();
    }

    public void agregarCurso() {
        Curso nuevoCurso = new Curso();
        nuevoCurso.setProfesor(new Usuario());
        this.cursoSeleccionado = nuevoCurso;
    }

    public void guardarCurso() {
        boolean esNuevo = this.cursoSeleccionado.getCodigoCurso() == null;

        cursoService.guardarCurso(this.cursoSeleccionado);

        cargarCursos();
        String mensaje = esNuevo ? "Curso agregado exitosamente" : "Curso actualizado";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", mensaje));

        PrimeFaces.current().executeScript("PF('dialogoCurso').hide()");
        PrimeFaces.current().ajax().update("formulario-cursos:mensajes", "formulario-cursos:tabla-cursos");

        this.cursoSeleccionado = null;
    }
    public void eliminarCurso() {
        cursoService.eliminarCurso(this.cursoSeleccionado);
        cursos.remove(this.cursoSeleccionado);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso eliminado"));

        PrimeFaces.current().ajax().update("formulario-cursos:mensajes", "formulario-cursos:tabla-cursos");

        this.cursoSeleccionado = null;
    }

    public void seleccionarCursoParaEdicion(Curso curso) {
        this.cursoSeleccionado = curso;
    }

    public void abrirDialogo() {
        if (this.cursoSeleccionado == null) {
            agregarCurso();
        }
    }
}