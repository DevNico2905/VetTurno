package com.nickbern.vetturno.service;

import com.nickbern.vetturno.dto.MascotaDTO;
import com.nickbern.vetturno.dto.MascotaRequest;
import com.nickbern.vetturno.exception.NegocioException;
import com.nickbern.vetturno.mapper.Mapper;
import com.nickbern.vetturno.model.Mascota;
import com.nickbern.vetturno.model.Propietario;
import com.nickbern.vetturno.repository.MascotaRepository;
import com.nickbern.vetturno.repository.PropietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaDTO crearMascota(MascotaRequest request){

        Propietario dbPropietario = propietarioRepository.findById(request.propietarioId())
                .orElseThrow(() -> new NegocioException("Propietario no encontrado"));

        var mascota = Mascota.builder()
                .nombre(request.nombre())
                .especie(request.especie())
                .raza(request.raza())
                .propietario(dbPropietario)
                .build();

        return Mapper.toDto(mascotaRepository.save(mascota));

    }

    public List<MascotaDTO> listarMascotas() {
        return mascotaRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }
}
