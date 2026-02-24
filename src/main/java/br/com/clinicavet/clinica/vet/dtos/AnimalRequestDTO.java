package br.com.clinicavet.clinica.vet.dtos;

public record AnimalRequestDTO(
        String nome,
        int idade,
        String sexo,
        String raca,
        String cor,
        String pelagem,
        Long clienteId
) {
}