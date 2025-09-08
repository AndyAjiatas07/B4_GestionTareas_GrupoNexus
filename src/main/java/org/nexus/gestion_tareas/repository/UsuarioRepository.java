package org.nexus.gestion_tareas.repository;
import org.nexus.gestion_tareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
