package com.nickbern.vetturno.mapper;

import com.nickbern.vetturno.dto.PropietarioRequest;
import com.nickbern.vetturno.model.Propietario;

public class Mapper {

    // De la entidad propietario a dto
    public static PropietarioRequest toDto(Propietario propietario) {

        if (propietario == null) return null;

        return PropietarioRequest.builder()
                .nombre(propietario.getNombre())
                .telefono(propietario.getTelefono())
                .email(propietario.getEmail())
                .build();
    }
}
