package com.nickbern.vetturno.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PropietarioRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El telefono es obligatorio")
        String telefono,

        @Email
        String email
) {}