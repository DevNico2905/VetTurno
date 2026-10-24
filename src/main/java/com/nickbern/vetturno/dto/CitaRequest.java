package com.nickbern.vetturno.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CitaRequest(
        // fechaHora, motivo, mascotaId, veterinarioId
        @NotNull(message = "La fecha y hora son obligatorias")
        @Future(message = "La cita debe ser en una fecha futura")
        LocalDateTime fechaHora,

        @NotBlank(message = "El motivo es obligatorio")
        String motivo,

        @NotNull(message = "La mascota es obligatoria")
        @Positive(message = "El id de la mascota debe ser positivo")
        Long mascotaId,

        @NotNull(message = "El veterinario es obligatorio")
        @Positive(message = "El id del veterinario debe ser positivo")
        Long veterinarioId
) {
}
