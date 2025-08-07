package com.technical.bank.cuenta.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaResponse {

    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;
    private Boolean estado;
    private Long clienteId;
}
