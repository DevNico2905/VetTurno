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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Transactional
    public CitaDTO crearCita(CitaRequest request) {

        if (request.fechaHora().isBefore(LocalDateTime.now())) {
            throw new NegocioException("La cita debe ser en una fecha futura");
        }

        Mascota mascota = mascotaRepository.findById(request.mascotaId())
                .orElseThrow(() -> new NegocioException("Mascota no encontrada"));

        Veterinario veterinario = veterinarioRepository.findById(request.veterinarioId())
                .orElseThrow(() -> new NegocioException("Veterinario no encontrado"));

        if (citaRepository.existsByVeterinarioIdAndFechaHora(request.veterinarioId(), request.fechaHora())) {
            throw new NegocioException("No disponibilidad. Veterinario con otra cita en ese horario.");
        }

        var cita = Cita.builder()
                .fechaHora(request.fechaHora())
                .motivo(request.motivo())
                .mascota(mascota)
                .veterinario(veterinario)
                .build();

        return Mapper.toDto(citaRepository.save(cita));
    }

    public List<CitaDTO> listarCita(){
        return citaRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

    public List<CitaDTO> listarCitaByVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioIdOrderByFechaHoraAsc(veterinarioId)
                .stream()
                .map(Mapper::toDto)
                .toList();
    }
}
