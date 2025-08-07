package com.technical.bank.cliente.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAndListClientes() throws Exception {
        // 1) Creamos un cliente nuevo con identificación única
        String payload = """
            {
              "nombre": "Carlos Pérez",
              "genero": "M",
              "edad": 40,
              "identificacion": "1999999994",
              "direccion": "Av. Siempre Viva 742",
              "telefono": "0987000000",
              "contrasena": "pass1234"
            }
            """;

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.identificacion").value("1999999994"));

        // 2) Listamos todos y comprobamos que existe un elemento con esa identificación
        mockMvc.perform(get("/api/clientes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].identificacion", hasItem("1999999994")));
    }
}
