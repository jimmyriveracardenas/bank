package com.technical.bank.cuenta.service;

import com.technical.bank.cuenta.dto.CuentaReporteDto;
import java.time.OffsetDateTime;
import java.util.List;

public interface ReporteService {
    List<CuentaReporteDto> generarReporte(
        Long clienteId,
        OffsetDateTime fechaInicio,
        OffsetDateTime fechaFin
    );
}
