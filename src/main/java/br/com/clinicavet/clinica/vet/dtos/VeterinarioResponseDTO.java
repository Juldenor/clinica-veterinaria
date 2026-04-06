package br.com.clinicavet.clinica.vet.dtos;

import br.com.clinicavet.clinica.vet.model.Veterinario;

public record VeterinarioResponseDTO(Long id, String nome, String email, String crmv) {
    public VeterinarioResponseDTO(Veterinario v) {
        this(v.getId(), v.getNome(), v.getEmail(), v.getCrmv());
    }
}
