package org.nexus.gestion_tareas.service;

import org.nexus.gestion_tareas.entity.Entrega;
import org.nexus.gestion_tareas.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService implements IEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Override
    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }

    @Override
    public Entrega buscarEntregaPorId(Integer codigo) {
        return entregaRepository.findById(codigo).orElse(null);
    }

    @Override
    public void guardarEntrega(Entrega entrega) {
        entregaRepository.save(entrega);
    }

    @Override
    public void eliminarEntrega(Entrega entrega) {
        entregaRepository.delete(entrega);
    }
}
