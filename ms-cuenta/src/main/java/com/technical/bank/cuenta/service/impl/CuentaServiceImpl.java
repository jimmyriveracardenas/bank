package com.technical.bank.cuenta.service.impl;

import com.technical.bank.cuenta.client.ClienteClient;
import com.technical.bank.cuenta.domain.Cuenta;
import com.technical.bank.cuenta.dto.*;
import com.technical.bank.cuenta.repository.CuentaRepository;
import com.technical.bank.cuenta.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepo;
    private final ClienteClient clienteClient;

    @Override
    public List<CuentaResponse> getAll() {
        return cuentaRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaResponse getByNumero(String numero) {
        Cuenta c = cuentaRepo.findById(numero)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
        return toResponse(c);
    }

    @Override
    public CuentaResponse create(CreateCuentaRequest req) {
        // Validar existencia de cliente
        if (clienteClient.getById(req.getClienteId()) == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Cliente no existe");
        }
        if (cuentaRepo.existsById(req.getNumeroCuenta())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Número de cuenta duplicado");
        }
        Cuenta c = Cuenta.builder()
                .numeroCuenta(req.getNumeroCuenta())
                .tipoCuenta(req.getTipoCuenta())
                .saldo(req.getSaldo())
                .estado(req.getEstado())
                .clienteId(req.getClienteId())
                .build();
        return toResponse(cuentaRepo.save(c));
    }

    @Override
    public CuentaResponse update(String numero, UpdateCuentaRequest req) {
        Cuenta c = cuentaRepo.findById(numero)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
        c.setTipoCuenta(req.getTipoCuenta());
        c.setEstado(req.getEstado());
        return toResponse(cuentaRepo.save(c));
    }

    @Override
    public void delete(String numero) {
        if (!cuentaRepo.existsById(numero)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cuenta no encontrada");
        }
        cuentaRepo.deleteById(numero);
    }

    private CuentaResponse toResponse(Cuenta c) {
        return CuentaResponse.builder()
                .numeroCuenta(c.getNumeroCuenta())
                .tipoCuenta(c.getTipoCuenta())
                .saldo(c.getSaldo())
                .estado(c.getEstado())
                .clienteId(c.getClienteId())
                .build();
    }
}
