package org.nexus.gestion_tareas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Entregas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoEntrega;

    @ManyToOne
    @JoinColumn(name = "codigoTarea")
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "codigoAlumno")
    private Usuario alumno;

    private String respuesta;

    private LocalDateTime fechaEntrega;

    private Double calificacion;
}
