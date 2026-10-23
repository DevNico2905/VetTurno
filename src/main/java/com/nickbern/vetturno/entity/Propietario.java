package com.nickbern.vetturno.entity;

// id, nombre, teléfono, email
//Nombre y teléfono obligatorios; email válido; puede tener varias mascotas.

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propietarios")
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Builder
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Email
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String telefono;

    @OneToMany(mappedBy = "propietario")
    @Builder.Default
    private List<Mascota> mascotas = new ArrayList<>();

}