package org.nexus.gestion_tareas.service;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Integer codigo);
    public void guardarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
}

