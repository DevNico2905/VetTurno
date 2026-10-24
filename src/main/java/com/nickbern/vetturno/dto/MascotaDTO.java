package com.nickbern.vetturno.dto;

import lombok.Builder;

@Builder
public record MascotaDTO(

        // id, nombre, especie, raza, propietarioId, propietarioNombre

        Long id,

        String nombre,

        String especie,

        String raza,

        Long propietarioId,

        String propietarioNombre
) {
}
