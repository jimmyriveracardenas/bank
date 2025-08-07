package com.technical.bank.cuenta.service.impl;

import com.technical.bank.cuenta.domain.Cuenta;
import com.technical.bank.cuenta.domain.Movimiento;
import com.technical.bank.cuenta.dto.*;
import com.technical.bank.cuenta.repository.*;
import com.technical.bank.cuenta.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movRepo;
    private final CuentaRepository cuentaRepo;

    @Override
    public List<MovimientoResponse> getAll() {
        return movRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponse create(CreateMovimientoRequest req) {
        Cuenta cuenta = cuentaRepo.findById(req.getNumeroCuenta())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuenta no encontrada"));

        BigDecimal nuevoSaldo = cuenta.getSaldo().add(req.getValor());
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Saldo insuficiente");
        }

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepo.save(cuenta);

        Movimiento mov = Movimiento.builder()
                .fecha(OffsetDateTime.now())
                .tipoMovimiento(req.getTipoMovimiento())
                .valor(req.getValor())
                .saldo(nuevoSaldo)
                .numeroCuenta(req.getNumeroCuenta())
                .build();

        Movimiento saved = movRepo.save(mov);
        return MovimientoResponse.builder()
                .id(saved.getId())
                .fecha(saved.getFecha())
                .tipoMovimiento(saved.getTipoMovimiento())
                .valor(saved.getValor())
                .saldo(saved.getSaldo())
                .numeroCuenta(saved.getNumeroCuenta())
                .build();
    }

    @Override
    public List<MovimientoResponse> getByCuenta(String numeroCuenta) {
        return movRepo.findByNumeroCuentaOrderByFechaDesc(numeroCuenta).stream()
                .map(m -> MovimientoResponse.builder()
                        .id(m.getId())
                        .fecha(m.getFecha())
                        .tipoMovimiento(m.getTipoMovimiento())
                        .valor(m.getValor())
                        .saldo(m.getSaldo())
                        .numeroCuenta(m.getNumeroCuenta())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponse update(Long id, UpdateMovimientoRequest req) {
        Movimiento mov = movRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
        // actualizar campos permitidos
        mov.setTipoMovimiento(req.getTipoMovimiento());
        BigDecimal diferencia = req.getValor().subtract(mov.getValor());
        BigDecimal nuevoSaldo = mov.getSaldo().add(diferencia);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "Saldo insuficiente tras actualización");
        }
        // actualizar saldo en movimiento y en cuenta
        mov.setValor(req.getValor());
        mov.setSaldo(nuevoSaldo);

        Cuenta cuenta = cuentaRepo.findById(mov.getNumeroCuenta())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuenta asociada no encontrada"));
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepo.save(cuenta);

        return toResponse(movRepo.save(mov));
    }

    @Override
    public void delete(Long id) {
        Movimiento mov = movRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
        // ajustar saldo de la cuenta al borrar:
        Cuenta cuenta = cuentaRepo.findById(mov.getNumeroCuenta())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuenta asociada no encontrada"));
        cuenta.setSaldo(cuenta.getSaldo().subtract(mov.getValor()));
        cuentaRepo.save(cuenta);

        movRepo.deleteById(id);
    }

    private MovimientoResponse toResponse(Movimiento m) {
        return MovimientoResponse.builder()
                .id(m.getId())
                .fecha(m.getFecha())
                .tipoMovimiento(m.getTipoMovimiento())
                .valor(m.getValor())
                .saldo(m.getSaldo())
                .numeroCuenta(m.getNumeroCuenta())
                .build();
    }
}
