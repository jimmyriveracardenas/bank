package com.technical.bank.cuenta.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private OffsetDateTime fecha;

    @NotBlank
    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @Column(nullable = false)
    private BigDecimal saldo;

    @NotBlank
    @Column(name = "numero_cuenta", length = 50, nullable = false)
    private String numeroCuenta;
}
