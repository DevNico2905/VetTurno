package com.nickbern.vetturno.dto;

import lombok.Builder;

@Builder
public record VeterinarioDTO(

        Long id,

        String nombre,

        String especialidad

) {
}
