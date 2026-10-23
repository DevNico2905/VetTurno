package com.nickbern.vetturno.repository;

import com.nickbern.vetturno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
