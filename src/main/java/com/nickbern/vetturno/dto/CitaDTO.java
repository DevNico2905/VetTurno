package com.nickbern.vetturno.dto;
import com.nickbern.vetturno.model.Mascota;
import com.nickbern.vetturno.model.Propietario;
import com.nickbern.vetturno.model.Veterinario;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CitaDTO(

        // id, fechaHora, motivo, mascota, propietario, veterinario

        Long id,
        LocalDateTime fechaHora,
        String motivo,
        String mascota,
        String propietario,
        String veterinario
) {
}
