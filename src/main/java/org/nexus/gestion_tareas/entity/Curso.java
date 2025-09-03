package org.nexus.gestion_tareas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "Cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCurso;

    private String nombreCurso;

    private String descripcionCurso;

    @ManyToOne
    @JoinColumn(name = "codigoProfesor", nullable = false)
    private Usuario profesor;

    @OneToMany(mappedBy = "curso")
    private List<Tarea> tareas;
}
