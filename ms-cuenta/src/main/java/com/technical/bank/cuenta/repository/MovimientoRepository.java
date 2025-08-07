package com.technical.bank.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.bank.cuenta.domain.Movimiento;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    
    List<Movimiento> findByNumeroCuentaOrderByFechaDesc(String numeroCuenta);

    List<Movimiento> findByNumeroCuentaAndFechaBetweenOrderByFechaAsc(
        String numeroCuenta,
        OffsetDateTime fechaInicio,
        OffsetDateTime fechaFin
    );
}
