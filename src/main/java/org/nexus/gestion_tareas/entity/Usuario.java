package org.nexus.gestion_tareas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUsuario;

    private String nombreUsuario;

    private String correoUsuario;

    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "profesor")
    private List<Tarea> tareas;

    @OneToMany(mappedBy = "alumno")
    private List<Entrega> entregas;

    public enum Rol {
        alumno, profesor
    }
}
