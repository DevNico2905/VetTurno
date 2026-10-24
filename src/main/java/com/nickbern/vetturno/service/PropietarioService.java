package com.nickbern.vetturno.service;

import com.nickbern.vetturno.dto.PropietarioDTO;
import com.nickbern.vetturno.dto.PropietarioRequest;
import com.nickbern.vetturno.exception.NegocioException;
import com.nickbern.vetturno.mapper.Mapper;
import com.nickbern.vetturno.model.Propietario;
import com.nickbern.vetturno.repository.PropietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioService {

    private final PropietarioRepository propietarioRepository;

    // CEAR
    public PropietarioDTO crearPropietario(PropietarioRequest request) {

        if (propietarioRepository.existsByTelefono(request.telefono())) {
            throw new NegocioException("Ya existe un propietario con el telefono: " + request.telefono());
        }

        var propietario = Propietario.builder()
                .nombre(request.nombre())
                .email(request.email())
                .telefono(request.telefono())
                .build();

        return Mapper.toDto(propietarioRepository.save(propietario));
    }

    // LISTAR

    public List<PropietarioDTO> listarPropietarios() {
        return propietarioRepository
                .findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

}
