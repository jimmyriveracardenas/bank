package com.technical.bank.cliente.controller;

import com.technical.bank.cliente.dto.*;
import com.technical.bank.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteResponse> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClienteResponse getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse create(@Valid @RequestBody CreateClienteRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public ClienteResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateClienteRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
