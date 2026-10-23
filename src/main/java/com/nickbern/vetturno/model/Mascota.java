package com.nickbern.vetturno.model;

// id, nombre, especie, raza, propietario
// Nombre, especie y propietario obligatorios; pertenece a un solo responsable.

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "mascotas")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String especie;

    private String raza;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;
}
