package com.technical.bank.cuenta.controller;

import com.technical.bank.cuenta.dto.CuentaReporteDto;
import com.technical.bank.cuenta.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    /**
     * GET /api/reportes?clienteId=1&fechaInicio=2025-08-01T00:00:00Z&fechaFin=2025-08-05T23:59:59Z
     */
    @GetMapping
    public List<CuentaReporteDto> reporteCuenta(
        @RequestParam Long clienteId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime fechaInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            OffsetDateTime fechaFin
    ) {
        return reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
    }
}
