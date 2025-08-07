package com.technical.bank.cuenta.service;

import com.technical.bank.cuenta.dto.*;
import java.util.List;

public interface CuentaService {

    List<CuentaResponse> getAll();

    CuentaResponse getByNumero(String numeroCuenta);

    CuentaResponse create(CreateCuentaRequest req);

    CuentaResponse update(String numeroCuenta, UpdateCuentaRequest req);
    
    void delete(String numeroCuenta);
}
