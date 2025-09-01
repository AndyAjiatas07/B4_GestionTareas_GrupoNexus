drop database if exists gestiondetareas_db;
create database gestiondetareas_db;
use gestiondetareas_db;
 
create table Profesores (
    codigoProfesor int auto_increment,
    nombreProfesor varchar(128) not null,
    correoProfesor varchar(128) not null,
		constraint pk_profesores primary key (codigoProfesor)
);
 
create table Cursos (
    codigoCurso int auto_increment,
    nombreCurso varchar(128) not null,
    descripcionCurso varchar(256) not null,
    codigoProfesor int,
		constraint pk_cursos primary key (codigoCurso),
		constraint fk_cursos_profesores foreign key(codigoProfesor)
			references profesores(codigoProfesor)
);
 
create table Tareas (
    codigoTarea int auto_increment,
    nombreTarea varchar(128) not null,
    descripcionTarea varchar(256) not null,
    fechaEntrega datetime not null,
    codigoCurso int,
		constraint pk_tareas primary key (codigoTarea),
		constraint fk_tareas_cursos foreign key(codigoCurso)
			references Cursos(codigoCurso)
);
 
create table Estudiantes (
    codigoEstudiante int auto_increment,
    nombreEstudiante varchar(128) not null,
    correoEstudiante varchar(128) not null,
    codigoCurso int,
		constraint pk_estudiantes primary key (codigoEstudiante),
		constraint fk_estudiantes_cursos foreign key(codigoCurso)
			references Cursos(codigoCurso)
);
 
create table Entregas (
	codigoEntrega int auto_increment,
    fechaEntrega datetime not null,
	calificacion int not null,
    codigoTarea int,
    codigoEstudiante int,
        constraint pk_entregas primary key (codigoEntrega),
		constraint fk_entregas_tareas foreign key(codigoTarea)
			references Tareas(codigoTarea),
        constraint fk_entregas_estudiantes foreign key(codigoEstudiante)
			references Estudiantes(codigoEstudiante)
);