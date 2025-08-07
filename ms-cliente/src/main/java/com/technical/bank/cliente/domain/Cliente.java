package com.technical.bank.cliente.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", nullable = false, unique = true)
    private Persona persona;

    @NotBlank
    private String contrasena;

    @NotNull
    private Boolean estado;
}
