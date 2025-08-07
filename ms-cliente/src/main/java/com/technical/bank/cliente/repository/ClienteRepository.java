package com.technical.bank.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.bank.cliente.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
