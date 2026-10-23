package com.nickbern.vetturno.dto;
import lombok.Builder;

@Builder
public record PropietarioDTO(

        Long id,

        String nombre,

        String telefono,

        String email
) {
}
