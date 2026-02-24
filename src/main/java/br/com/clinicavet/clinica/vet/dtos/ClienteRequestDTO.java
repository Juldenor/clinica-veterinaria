package br.com.clinicavet.clinica.vet.dtos;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
}