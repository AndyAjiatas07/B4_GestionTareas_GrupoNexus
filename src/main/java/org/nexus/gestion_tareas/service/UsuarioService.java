package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Usuario;
import org.nexus.gestion_tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override

