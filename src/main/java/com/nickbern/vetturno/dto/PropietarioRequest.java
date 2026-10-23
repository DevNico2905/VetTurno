package com.nickbern.vetturno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PropietarioRequest(
        @NotBlank String nombre,
        @NotBlank String telefono,
        @Email String email
) {}