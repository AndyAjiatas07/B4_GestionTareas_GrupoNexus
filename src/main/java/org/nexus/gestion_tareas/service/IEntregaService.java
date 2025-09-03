package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Entrega;
import java.util.List;

public interface IEntregaService {
    public List<Entrega> listarEntregas();
    public Entrega buscarEntregaPorId(Integer codigo);
    public void guardarEntrega(Entrega entrega);
    public void eliminarEntrega(Entrega entrega);
}
