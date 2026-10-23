package com.nickbern.vetturno.model;

// id, fechaHora, motivo, mascota, veterinario
// Fecha futura; motivo obligatorio; no puede coincidir con otra cita del mismo veterinario.

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @NotBlank
    @Column(nullable = false)
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;
}
