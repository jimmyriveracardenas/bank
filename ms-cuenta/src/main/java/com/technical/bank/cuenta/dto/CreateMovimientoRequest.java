package com.technical.bank.cuenta.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMovimientoRequest {
    
    @NotBlank
    private String numeroCuenta;

    @NotBlank
    private String tipoMovimiento;

    @NotNull
    private BigDecimal valor;
}
