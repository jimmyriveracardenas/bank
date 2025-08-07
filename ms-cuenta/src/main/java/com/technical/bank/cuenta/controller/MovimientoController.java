package com.technical.bank.cuenta.controller;

import com.technical.bank.cuenta.dto.*;
import com.technical.bank.cuenta.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService service;

    @GetMapping
    public List<MovimientoResponse> listAll() {
        return service.getAll();
    }

    @GetMapping("/cuenta/{numero}")
    public List<MovimientoResponse> listByCuenta(@PathVariable String numero) {
        return service.getByCuenta(numero);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoResponse create(@Valid @RequestBody CreateMovimientoRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public MovimientoResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMovimientoRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
