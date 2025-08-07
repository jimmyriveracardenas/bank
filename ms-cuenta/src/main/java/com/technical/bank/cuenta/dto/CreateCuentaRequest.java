package com.technical.bank.cuenta.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCuentaRequest {
    
    @NotBlank
    private String numeroCuenta;

    @NotBlank
    private String tipoCuenta;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private Boolean estado;

    @NotNull
    private Long clienteId;
}
