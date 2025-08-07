package com.technical.bank.cliente.service.impl;

import com.technical.bank.cliente.domain.*;
import com.technical.bank.cliente.dto.*;
import com.technical.bank.cliente.repository.*;
import com.technical.bank.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepo;
    private final PersonaRepository personaRepo;

    @Override
    public List<ClienteResponse> getAll() {
        return clienteRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse getById(Long id) {
        Cliente c = clienteRepo.findById(id)
                .orElseThrow(() -> 
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        return toResponse(c);
    }

    @Override
    public ClienteResponse create(CreateClienteRequest req) {
        if (personaRepo.existsByIdentificacion(req.getIdentificacion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identificación duplicada");
        }
        Persona p = Persona.builder()
                .nombre(req.getNombre())
                .genero(req.getGenero())
                .edad(req.getEdad())
                .identificacion(req.getIdentificacion())
                .direccion(req.getDireccion())
                .telefono(req.getTelefono())
                .build();
        Cliente c = Cliente.builder()
                .persona(p)
                .contrasena(req.getContrasena())
                .estado(true)
                .build();
        Cliente saved = clienteRepo.save(c);
        return toResponse(saved);
    }

    @Override
    public ClienteResponse update(Long id, UpdateClienteRequest req) {
        Cliente c = clienteRepo.findById(id)
                .orElseThrow(() -> 
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        Persona p = c.getPersona();
        p.setNombre(req.getNombre());
        p.setDireccion(req.getDireccion());
        p.setTelefono(req.getTelefono());
        c.setEstado(req.getEstado());
        Cliente updated = clienteRepo.save(c);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        clienteRepo.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente c) {
        Persona p = c.getPersona();
        return ClienteResponse.builder()
                .clienteId(c.getId())
                .personaId(p.getId())
                .nombre(p.getNombre())
                .genero(p.getGenero())
                .edad(p.getEdad())
                .identificacion(p.getIdentificacion())
                .direccion(p.getDireccion())
                .telefono(p.getTelefono())
                .estado(c.getEstado())
                .build();
    }
}
