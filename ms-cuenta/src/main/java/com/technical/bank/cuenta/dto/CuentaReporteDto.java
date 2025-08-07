package com.technical.bank.cuenta.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaReporteDto {
    
    private String numeroCuenta;
    private BigDecimal saldoActual;
    private List<MovimientoReporteDto> movimientos;
}