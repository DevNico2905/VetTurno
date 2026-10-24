package com.nickbern.vetturno.service;

import com.nickbern.vetturno.dto.VeterinarioDTO;
import com.nickbern.vetturno.dto.VeterinarioRequest;
import com.nickbern.vetturno.mapper.Mapper;
import com.nickbern.vetturno.model.Veterinario;
import com.nickbern.vetturno.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    // CREAR
    public VeterinarioDTO crearVeterinario(VeterinarioRequest request) {

        var veterinario = Veterinario.builder()
                .nombre(request.nombre())
                .especialidad(request.especialidad())
                .build();

        return Mapper.toDto(veterinarioRepository.save(veterinario));
    }

    // LISTAR
    public List<VeterinarioDTO> listarVeterinarios() {
        return veterinarioRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

}
