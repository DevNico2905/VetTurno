package com.nickbern.vetturno.controller;

import com.nickbern.vetturno.dto.MascotaDTO;
import com.nickbern.vetturno.dto.MascotaRequest;
import com.nickbern.vetturno.service.MascotaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@AllArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaDTO> crearMascota(@Valid @RequestBody MascotaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.crearMascota(request));
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> obtenerMascotas() {
        return ResponseEntity.ok(mascotaService.listarMascotas());
    }

}
