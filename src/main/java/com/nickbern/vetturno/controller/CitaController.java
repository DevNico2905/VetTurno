package com.nickbern.vetturno.controller;

import com.nickbern.vetturno.dto.CitaDTO;
import com.nickbern.vetturno.dto.CitaRequest;
import com.nickbern.vetturno.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaDTO> crearCita(@Valid @RequestBody CitaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crearCita(request));
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarCitas() {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.listarCita());
    }

    @GetMapping("/veterinario/{id}")
    public ResponseEntity<List<CitaDTO>> listarCitasByVeterinario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(citaService.listarCitaByVeterinario(id));
    }
}
