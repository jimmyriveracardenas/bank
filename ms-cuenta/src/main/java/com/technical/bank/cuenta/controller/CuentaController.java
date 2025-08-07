package com.technical.bank.cuenta.controller;

import com.technical.bank.cuenta.dto.*;
import com.technical.bank.cuenta.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService service;

    @GetMapping
    public List<CuentaResponse> list() {
        return service.getAll();
    }

    @GetMapping("/{numero}")
    public CuentaResponse getOne(@PathVariable String numero) {
        return service.getByNumero(numero);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaResponse create(@Valid @RequestBody CreateCuentaRequest req) {
        return service.create(req);
    }

    @PutMapping("/{numero}")
    public CuentaResponse update(
            @PathVariable String numero,
            @Valid @RequestBody UpdateCuentaRequest req) {
        return service.update(numero, req);
    }

    @DeleteMapping("/{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String numero) {
        service.delete(numero);
    }
}
