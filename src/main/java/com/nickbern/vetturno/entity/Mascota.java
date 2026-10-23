package com.nickbern.vetturno.entity;

// id, nombre, especie, raza, propietario
// Nombre, especie y propietario obligatorios; pertenece a un solo responsable.

import jakarta.persistence.*;
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

    private String nombre;

    private String especie;

    private String raza;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;
}
