package com.technical.bank.cuenta.client;

import com.technical.bank.cuenta.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente", url = "${ms-cliente.url}")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteDto getById(@PathVariable("id") Long id);
}
