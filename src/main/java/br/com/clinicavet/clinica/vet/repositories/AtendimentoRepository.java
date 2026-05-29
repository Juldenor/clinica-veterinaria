package br.com.clinicavet.clinica.vet.repositories;

import br.com.clinicavet.clinica.vet.model.Atendimento;
import br.com.clinicavet.clinica.vet.model.StatusAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByAnimalIdOrderByDataAtendimentoDesc(Long animalId);

    long countByStatus(StatusAtendimento status);

    List<Atendimento> findByStatusOrderByDataAtendimentoAsc(StatusAtendimento status);

    List<Atendimento> findTop5ByStatusAndDataAtendimentoGreaterThanEqualOrderByDataAtendimentoAsc(
            StatusAtendimento status,
            LocalDate dataAtendimento
    );
}
