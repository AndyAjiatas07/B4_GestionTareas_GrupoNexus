package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Usuario;
import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Integer codigo);
    public void guardarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
}
