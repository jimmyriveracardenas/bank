package com.technical.bank.cuenta.service;

import com.technical.bank.cuenta.dto.MovimientoResponse;
import com.technical.bank.cuenta.dto.UpdateMovimientoRequest;
import com.technical.bank.cuenta.dto.CreateMovimientoRequest;
import java.util.List;


public interface MovimientoService {

    List<MovimientoResponse> getAll();

    List<MovimientoResponse> getByCuenta(String numeroCuenta);

    MovimientoResponse create(CreateMovimientoRequest req);

    MovimientoResponse update(Long id, UpdateMovimientoRequest req);

    void delete(Long id);

}
