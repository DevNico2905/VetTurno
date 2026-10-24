package com.nickbern.vetturno.controller;

import com.nickbern.vetturno.dto.PropietarioDTO;
import com.nickbern.vetturno.dto.PropietarioRequest;
import com.nickbern.vetturno.service.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@RequiredArgsConstructor
public class PropietarioController {

    private final PropietarioService propietarioService;

    @PostMapping
    public ResponseEntity<PropietarioDTO> crearPropietario(@Valid @RequestBody PropietarioRequest propietarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propietarioService.crearPropietario(propietarioRequest));
    }

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> listarPropietarios() {
        return ResponseEntity.status(HttpStatus.OK).body(propietarioService.listarPropietarios());
    }
}
