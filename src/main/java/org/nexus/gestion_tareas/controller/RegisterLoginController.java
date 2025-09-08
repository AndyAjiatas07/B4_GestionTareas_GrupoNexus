package org.nexus.gestion_tareas.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Scope;
import org.nexus.gestion_tareas.entity.RegLog;
import org.nexus.gestion_tareas.repository.RegLogRepository;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

@Named("registerLoginController")
@SessionScoped
public class RegisterLoginController implements Serializable {

    private RegLog usuario = new RegLog();
    private String mensaje;

    @Autowired
    private RegLogRepository regLogRepository;

    // Constructor vacío obligatorio para JSF
    public RegisterLoginController() {}

    public RegLog getUsuario() { return usuario; }
    public void setUsuario(RegLog usuario) { this.usuario = usuario; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    // Registrar usuario
    public String registrar() {
        if (regLogRepository.existsByEmail(usuario.getEmail())) {
            mensaje = "Este correo ya está registrado";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
            return null;
        }
        // Asignar rol por defecto si no viene especificado
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("ALUMNO");
        }
        regLogRepository.save(usuario); // Guardar en la base de datos
        mensaje = "Registro exitoso. Puedes iniciar sesión.";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
        usuario = new RegLog(); // Limpiar formulario
        return "login.xhtml?faces-redirect=true";
    }

    // Login con diferenciación por rol
    public String login() {
        RegLog u = regLogRepository.findByEmailAndPassword(usuario.getEmail(), usuario.getPassword());
        if (u != null) {
            usuario = u; // Guardar usuario logueado

            // Diferenciar por rol
            if ("Profesor".equalsIgnoreCase(usuario.getRol())) {
                mensaje = "Bienvenido profesor: " + usuario.getNombre();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
                return "catalogoProfesor.xhtml?faces-redirect=true"; // Página específica para profesor
            } else if ("Alumno".equalsIgnoreCase(usuario.getRol())) {
                mensaje = "Bienvenido alumno: " + usuario.getNombre();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
                return "catalogoAlumno.xhtml?faces-redirect=true"; // Página específica para alumno
            } else {
                // Rol desconocido
                mensaje = "Rol de usuario no reconocido";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
                return null;
            }
        } else {
            mensaje = "Usuario o contraseña incorrectos";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
            return null;
        }
    }

    // Logout
    public String logout() {
        usuario = new RegLog(); // Limpiar sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
}
