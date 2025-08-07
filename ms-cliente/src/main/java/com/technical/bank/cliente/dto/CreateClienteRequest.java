package com.technical.bank.cliente.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClienteRequest {
    // Datos de persona
    @NotBlank @Size(max = 100)
    private String nombre;

    @NotNull @Pattern(regexp = "M|F|O")
    private String genero;

    @NotNull @Min(0)
    private Integer edad;

    @NotBlank @Size(max = 50)
    private String identificacion;

    private String direccion;
    private String telefono;

    // Datos de cliente
    @NotBlank
    private String contrasena;
}
