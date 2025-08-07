package com.technical.bank.cliente.service;

import com.technical.bank.cliente.dto.*;
import java.util.List;

public interface ClienteService {
    List<ClienteResponse> getAll();
    ClienteResponse getById(Long id);
    ClienteResponse create(CreateClienteRequest req);
    ClienteResponse update(Long id, UpdateClienteRequest req);
    void delete(Long id);
}
