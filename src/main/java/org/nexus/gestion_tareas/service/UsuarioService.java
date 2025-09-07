package org.nexus.gestion_tareas.service;
import jakarta.transaction.Transactional;
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
    @Transactional
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        // Carga explícita de las colecciones 'lazy' antes de que la sesión se cierre.
        usuarios.forEach(usuario -> {
            usuario.getCursos().size();
            usuario.getTareas().size();
            usuario.getEntregas().size();
        });
        return usuarios;
    }

    @Override
    @Transactional
    public Usuario buscarUsuarioPorId(Integer codigo) {
        Usuario usuario = usuarioRepository.findById(codigo).orElse(null);
        if (usuario != null) {
            // Carga explícita de las colecciones 'lazy' para un solo usuario.
            usuario.getCursos().size();
            usuario.getTareas().size();
            usuario.getEntregas().size();
        }
        return usuario;
    }

    @Override
    @Transactional
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}