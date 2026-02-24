package br.com.clinicavet.clinica.vet.dtos;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String telefone;
}