package br.com.clinicavet.clinica.vet.dtos;

import br.com.clinicavet.clinica.vet.model.Animal;

public record AnimalResponseDTO(
        Long id,
        String nome,
        int idade,
        String raca,
        String nomeDono, // Em vez de mandar o objeto cliente, mandamos só o nome!
        String cpfDono // E também o CPF para mostrar no Front
) {
    // Construtor inteligente que converte a Entidade no DTO
    public AnimalResponseDTO(Animal animal) {
        this(
                animal.getId(),
                animal.getNome(),
                animal.getIdade(),
                animal.getRaca(),
                animal.getCliente() != null ? animal.getCliente().getNome() : "Sem dono",
                animal.getCliente() != null ? animal.getCliente().getCpf() : "N/A"
        );
    }
}