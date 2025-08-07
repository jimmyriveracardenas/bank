package com.technical.bank.cuenta.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovimientoRequest {
    
    @NotBlank
    private String tipoMovimiento;

    @NotNull
    private BigDecimal valor;
}
