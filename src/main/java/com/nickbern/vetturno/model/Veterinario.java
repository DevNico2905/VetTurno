package com.nickbern.vetturno.model;

// id, nombre, especialidad
// Nombre y especialidad obligatorios; solo ADMIN puede registrarlo.

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "veterinarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String especialidad;

}
