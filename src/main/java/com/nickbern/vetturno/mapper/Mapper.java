package com.nickbern.vetturno.mapper;

import com.nickbern.vetturno.dto.CitaDTO;
import com.nickbern.vetturno.dto.MascotaDTO;
import com.nickbern.vetturno.dto.PropietarioDTO;
import com.nickbern.vetturno.dto.VeterinarioDTO;
import com.nickbern.vetturno.model.Cita;
import com.nickbern.vetturno.model.Mascota;
import com.nickbern.vetturno.model.Propietario;
import com.nickbern.vetturno.model.Veterinario;

public class Mapper {

    // Propietario Entity -> Dto
    public static PropietarioDTO toDto(Propietario propietario){

        if (propietario == null) return null;

        return PropietarioDTO.builder()
                .id(propietario.getId())
                .nombre(propietario.getNombre())
                .telefono(propietario.getTelefono())
                .email(propietario.getEmail())
                .build();
    }

    // Veterinario Entity -> Dto
    public static VeterinarioDTO toDto(Veterinario veterinario){

        if (veterinario == null) return null;

        return VeterinarioDTO.builder()
                .id(veterinario.getId())
                .nombre(veterinario.getNombre())
                .especialidad(veterinario.getEspecialidad())
                .build();
    }

    // Mascota Entity -> Dto
    public static MascotaDTO toDto(Mascota mascota){
        
        if (mascota == null) return null;
        
        return MascotaDTO.builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .especie(mascota.getEspecie())
                .raza(mascota.getRaza())
                .propietarioId(mascota.getPropietario().getId())
                .propietarioNombre(mascota.getPropietario().getNombre())
                .build();
    }

    public static CitaDTO toDto(Cita cita){

        if (cita == null) return null;

        return CitaDTO.builder()
                .id(cita.getId())
                .fechaHora(cita.getFechaHora())
                .motivo(cita.getMotivo())
                .mascota(cita.getMascota().getNombre())
                .propietario(cita.getMascota().getPropietario().getNombre())
                .veterinario(cita.getVeterinario().getNombre())
                .build();
    }

}
