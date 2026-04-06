package br.com.clinicavet.clinica.vet.dtos;

import java.time.LocalDate;

public record AtendimentoRequestDTO(
    Long animalId,
    Long veterinarioId,
    LocalDate dataAtendimento,
    String descricao,
    Double valor,
    String status
) {}
