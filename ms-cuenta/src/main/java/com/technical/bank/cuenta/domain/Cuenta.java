package com.technical.bank.cuenta.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @Column(name = "numero_cuenta", length = 50)
    private String numeroCuenta;

    @NotBlank
    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @NotNull
    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldo;

    @NotNull
    @Column(nullable = false)
    private Boolean estado;

    @NotNull
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}
