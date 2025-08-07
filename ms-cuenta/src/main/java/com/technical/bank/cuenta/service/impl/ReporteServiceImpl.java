package com.technical.bank.cuenta.service.impl;

import com.technical.bank.cuenta.client.ClienteClient;
import com.technical.bank.cuenta.domain.Cuenta;
import com.technical.bank.cuenta.dto.*;
import com.technical.bank.cuenta.repository.*;
import com.technical.bank.cuenta.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final CuentaRepository cuentaRepo;
    private final MovimientoRepository movRepo;
    private final ClienteClient clienteClient;

    @Override
    public List<CuentaReporteDto> generarReporte(
            Long clienteId,
            OffsetDateTime fechaInicio,
            OffsetDateTime fechaFin
    ) {
        // Validar existencia de cliente
        if (clienteClient.getById(clienteId) == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }

        // Obtener todas las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepo.findByClienteId(clienteId);

        return cuentas.stream().map(c -> {
            // Para cada cuenta, filtrar movimientos en el rango
            List<MovimientoReporteDto> movs = movRepo
                .findByNumeroCuentaAndFechaBetweenOrderByFechaAsc(
                    c.getNumeroCuenta(),
                    fechaInicio,
                    fechaFin)
                .stream()
                .map(m -> MovimientoReporteDto.builder()
                          .fecha(m.getFecha())
                          .tipoMovimiento(m.getTipoMovimiento())
                          .valor(m.getValor())
                          .saldo(m.getSaldo())
                          .build())
                .collect(Collectors.toList());

            return CuentaReporteDto.builder()
                    .numeroCuenta(c.getNumeroCuenta())
                    .saldoActual(c.getSaldo())
                    .movimientos(movs)
                    .build();
        }).collect(Collectors.toList());
    }
}
