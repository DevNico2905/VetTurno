package com.nickbern.vetturno.service;

import com.nickbern.vetturno.dto.CitaDTO;
import com.nickbern.vetturno.dto.CitaRequest;
import com.nickbern.vetturno.exception.NegocioException;
import com.nickbern.vetturno.mapper.Mapper;
import com.nickbern.vetturno.model.Cita;
import com.nickbern.vetturno.model.Mascota;
import com.nickbern.vetturno.model.Veterinario;
import com.nickbern.vetturno.repository.CitaRepository;
import com.nickbern.vetturno.repository.MascotaRepository;
import com.nickbern.vetturno.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaDTO crearCita(CitaRequest request) {

        if (!mascotaRepository.existsById(request.mascotaId())) {
            throw new NegocioException("Mascota inexistente");
        }

        if (!veterinarioRepository.existsById(request.veterinarioId())) {
            throw new NegocioException("Veterinario inexistente");
        }

        if (citaRepository.existsByVeterinarioIdAndFechaHora(request.veterinarioId(), request.fechaHora())) {
            throw new NegocioException("No disponibilidad. Veterinario con otra cita en ese horario.");
        }

        Mascota mascota = mascotaRepository.findById(request.mascotaId())
                .orElseThrow(() -> new NegocioException("Mascota no encontrado"));

        Veterinario veterinario = veterinarioRepository.findById(request.veterinarioId())
                .orElseThrow(() -> new NegocioException("Veterinario no encontrado"));

        var cita = Cita.builder()
                .fechaHora(request.fechaHora())
                .motivo(request.motivo())
                .mascota(mascota)
                .veterinario(veterinario)
                .build();

        return Mapper.toDto(citaRepository.save(cita));
    }
}
