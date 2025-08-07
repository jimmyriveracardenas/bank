package com.technical.bank.cuenta.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCuentaRequest {
    
    @NotBlank
    private String tipoCuenta;

    @NotNull
    private Boolean estado;
}
