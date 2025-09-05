drop database if exists gestion_academica_db;
create database gestion_academica_db;
use gestion_academica_db;

-- Usuarios (profesor o alumno)
create table Usuarios (
    codigoUsuario int auto_increment,
    nombreUsuario varchar(128) not null,
    correoUsuario varchar(128) not null,
    contrasena varchar(64) not null,
    rol enum('alumno','profesor') not null,
    constraint pk_usuarios primary key (codigoUsuario)
);

-- Cursos (materias fijas)
create table Cursos (
    codigoCurso int auto_increment,
    nombreCurso varchar(128) not null,
    descripcionCurso varchar(256),
    codigoProfesor int not null,
    constraint pk_cursos primary key (codigoCurso),
    constraint fk_cursos_profesores foreign key (codigoProfesor)
        references Usuarios(codigoUsuario)
);

-- Tareas creadas por el profesor en un curso
create table Tareas (
    codigoTarea int auto_increment,
    titulo varchar(128) not null,
    descripcion varchar(256) not null,
    fechaEntrega datetime not null,
    codigoCurso int,
    codigoProfesor int,
    constraint pk_tareas primary key (codigoTarea),
    constraint fk_tareas_cursos foreign key (codigoCurso)
        references Cursos(codigoCurso),
    constraint fk_tareas_profesor foreign key (codigoProfesor)
        references Usuarios(codigoUsuario)
);

-- Entregas de alumnos
create table Entregas (
    codigoEntrega int auto_increment,
    codigoTarea int,
    codigoAlumno int,
    respuesta varchar(256),
    fechaEntrega datetime not null,
    calificacion double,
    constraint pk_entregas primary key (codigoEntrega),
    constraint fk_entregas_tareas foreign key (codigoTarea)
        references Tareas(codigoTarea),
    constraint fk_entregas_alumno foreign key (codigoAlumno)
        references Usuarios(codigoUsuario)
);
INSERT INTO Usuarios (nombreUsuario, correoUsuario, contrasena, rol) VALUES
('Carlos López', 'carlos.lopez@uni.edu', 'contraseña123', 'profesor'),
('Ana Pérez', 'ana.perez@uni.edu', 'contraseña123', 'alumno'),
('Juan Rodríguez', 'juan.rodriguez@uni.edu', 'contraseña123', 'alumno'),
('Laura Martínez', 'laura.martinez@uni.edu', 'contraseña123', 'alumno');
INSERT INTO Cursos (nombreCurso, descripcionCurso, codigoProfesor) VALUES
('Matemáticas I', 'Curso introductorio a las matemáticas', 1),
('Historia Universal', 'Estudio de la historia desde la antigüedad hasta la modernidad', 1);
INSERT INTO Tareas (titulo, descripcion, fechaEntrega, codigoCurso, codigoProfesor) VALUES
('Ejercicio 1: Álgebra', 'Resolver ejercicios básicos de álgebra', '2025-09-10 23:59:00', 1, 1),
('Examen de Historia', 'Estudio de eventos históricos claves', '2025-09-15 23:59:00', 2, 1);
INSERT INTO Entregas (codigoTarea, codigoAlumno, respuesta, fechaEntrega, calificacion) VALUES
(1, 2, 'Respuestas completas del ejercicio de álgebra', '2025-09-09 15:30:00', 8.5),
(1, 3, 'Respuestas del ejercicio de álgebra', '2025-09-09 16:00:00', 7.0),
(2, 4, 'Ensayo sobre la Revolución Francesa', '2025-09-14 20:00:00', 9.0);