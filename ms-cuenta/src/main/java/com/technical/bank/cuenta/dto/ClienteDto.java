package com.technical.bank.cuenta.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
    
    private Long clienteId;
    private Long personaId;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Boolean estado;
}
