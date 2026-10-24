package com.nickbern.vetturno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MascotaRequest(
        // nombre, especie, raza, propietarioId

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "La especie es obligatoria")
        String especie,

        String raza,

        @NotNull(message = "El propietario es obligatorio")
        @Positive(message = "El id del propietario debe ser positivo")
        Long propietarioId
) {
}
