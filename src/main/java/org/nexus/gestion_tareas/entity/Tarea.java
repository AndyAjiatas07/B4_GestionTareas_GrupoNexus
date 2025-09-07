package org.nexus.gestion_tareas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoTarea;

    private String titulo;

    private String descripcion;

    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "codigoCurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "codigoProfesor")
    private Usuario profesor;

    @OneToMany(mappedBy = "tarea")
    private List<Entrega> entregas;
}
