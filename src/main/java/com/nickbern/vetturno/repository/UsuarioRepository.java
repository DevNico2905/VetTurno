package com.nickbern.vetturno.repository;

import com.nickbern.vetturno.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
