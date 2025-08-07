package com.technical.bank.cliente.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateClienteRequest {
    // Solo permitimos cambiar estos campos
    @NotBlank @Size(max = 100)
    private String nombre;

    private String direccion;
    private String telefono;

    @NotNull
    private Boolean estado;
}
