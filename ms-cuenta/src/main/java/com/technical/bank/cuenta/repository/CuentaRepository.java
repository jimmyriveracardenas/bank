package com.technical.bank.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.bank.cuenta.domain.Cuenta;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    
    List<Cuenta> findByClienteId(Long clienteId);
}
