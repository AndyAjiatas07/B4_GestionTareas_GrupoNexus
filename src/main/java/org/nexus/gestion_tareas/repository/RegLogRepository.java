package org.nexus.gestion_tareas.repository;

import org.nexus.gestion_tareas.entity.RegLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegLogRepository extends JpaRepository<RegLog, Integer> {
    RegLog findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
