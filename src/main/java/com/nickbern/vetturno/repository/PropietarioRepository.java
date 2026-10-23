package com.nickbern.vetturno.repository;

import com.nickbern.vetturno.model.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropietarioRepository extends JpaRepository<Propietario, Long> {

    boolean existsByTelefono(String telefono);

}
