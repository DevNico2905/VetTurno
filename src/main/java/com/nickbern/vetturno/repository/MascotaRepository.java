package com.nickbern.vetturno.repository;

import com.nickbern.vetturno.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
