package com.technical.bank.cliente.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testClienteBuilderAndAccessors() {
        // Primero construimos una Persona de prueba
        Persona persona = Persona.builder()
                .id(1L)
                .nombre("Test User")
                .genero("M")
                .edad(30)
                .identificacion("1712345678")
                .direccion("Calle Falsa 123")
                .telefono("0999001122")
                .build();

        // Ahora construimos un Cliente usando el builder de Lombok
        Cliente cliente = Cliente.builder()
                .id(10L)
                .persona(persona)
                .contrasena("s3cr3t!")
                .estado(true)
                .build();

        // Verificamos que el objeto no sea null y que los getters devuelvan lo esperado
        assertNotNull(cliente);
        assertEquals(10L, cliente.getId());
        assertSame(persona, cliente.getPersona());
        assertEquals("s3cr3t!", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }
}
