package com.technical.bank.cliente.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotNull
    @Pattern(regexp = "M|F|O")
    @Column(length = 1)
    private String genero;

    @NotNull
    @Min(0)
    private Integer edad;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String identificacion;

    private String direccion;

    private String telefono;
}
