package br.com.clinicavet.clinica.vet.dtos;

import br.com.clinicavet.clinica.vet.model.Atendimento;
import java.time.LocalDate;

public record AtendimentoResponseDTO(
    Long id,
    String animalNome,
    String veterinarioNome,
    LocalDate dataAtendimento,
    String descricao,
    Double valor,
    String status
) {
    public AtendimentoResponseDTO(Atendimento a) {
        this(
            a.getId(),
            a.getAnimal() != null ? a.getAnimal().getNome() : "Desconhecido",
            a.getVeterinario() != null ? a.getVeterinario().getNome() : "Desconhecido",
            a.getDataAtendimento(),
            a.getDescricao(),
            a.getValor(),
            a.getStatus() != null ? a.getStatus().name() : ""
        );
    }
}
