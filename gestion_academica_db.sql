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
