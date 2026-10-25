package com.nickbern.vetturno.repository;

import com.nickbern.vetturno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);

    List<Cita> findByVeterinarioIdOrderByFechaHoraAsc(Long veterinarioId);
    
}
