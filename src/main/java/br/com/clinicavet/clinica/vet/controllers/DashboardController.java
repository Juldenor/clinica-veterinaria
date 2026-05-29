package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.AtendimentoResponseDTO;
import br.com.clinicavet.clinica.vet.dtos.DashboardResumoDTO;
import br.com.clinicavet.clinica.vet.model.Atendimento;
import br.com.clinicavet.clinica.vet.model.StatusAtendimento;
import br.com.clinicavet.clinica.vet.repositories.AnimalRepository;
import br.com.clinicavet.clinica.vet.repositories.AtendimentoRepository;
import br.com.clinicavet.clinica.vet.repositories.ClienteRepository;
import br.com.clinicavet.clinica.vet.repositories.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @GetMapping("/resumo")
    public ResponseEntity<DashboardResumoDTO> resumo() {
        LocalDate hoje = LocalDate.now();
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        List<Atendimento> agendados = atendimentoRepository.findByStatusOrderByDataAtendimentoAsc(StatusAtendimento.AGENDADO);

        long concluidos = atendimentoRepository.countByStatus(StatusAtendimento.CONCLUIDO);
        long cancelados = atendimentoRepository.countByStatus(StatusAtendimento.CANCELADO);
        long totalAtendimentos = atendimentos.size();
        long atrasados = agendados.stream()
                .filter(atendimento -> atendimento.getDataAtendimento() != null && atendimento.getDataAtendimento().isBefore(hoje))
                .count();
        long proximos7Dias = agendados.stream()
                .filter(atendimento -> atendimento.getDataAtendimento() != null)
                .filter(atendimento -> !atendimento.getDataAtendimento().isBefore(hoje))
                .filter(atendimento -> !atendimento.getDataAtendimento().isAfter(hoje.plusDays(7)))
                .count();

        double receitaRealizada = atendimentos.stream()
                .filter(atendimento -> atendimento.getStatus() == StatusAtendimento.CONCLUIDO)
                .mapToDouble(this::valorSeguro)
                .sum();
        double receitaPrevista = agendados.stream()
                .filter(atendimento -> atendimento.getDataAtendimento() == null || !atendimento.getDataAtendimento().isBefore(hoje))
                .mapToDouble(this::valorSeguro)
                .sum();
        double ticketMedio = concluidos == 0 ? 0 : receitaRealizada / concluidos;
        double taxaConclusao = totalAtendimentos == 0 ? 0 : (concluidos * 100.0) / totalAtendimentos;

        List<AtendimentoResponseDTO> proximosAtendimentos = atendimentoRepository
                .findTop5ByStatusAndDataAtendimentoGreaterThanEqualOrderByDataAtendimentoAsc(StatusAtendimento.AGENDADO, hoje)
                .stream()
                .map(AtendimentoResponseDTO::new)
                .toList();

        List<AtendimentoResponseDTO> atendimentosAtrasados = agendados.stream()
                .filter(atendimento -> atendimento.getDataAtendimento() != null && atendimento.getDataAtendimento().isBefore(hoje))
                .map(AtendimentoResponseDTO::new)
                .toList();

        DashboardResumoDTO resumo = new DashboardResumoDTO(
                animalRepository.count(),
                clienteRepository.count(),
                veterinarioRepository.count(),
                totalAtendimentos,
                atendimentoRepository.countByStatus(StatusAtendimento.AGENDADO),
                concluidos,
                cancelados,
                atrasados,
                proximos7Dias,
                receitaRealizada,
                receitaPrevista,
                ticketMedio,
                taxaConclusao,
                proximosAtendimentos,
                atendimentosAtrasados
        );

        return ResponseEntity.ok(resumo);
    }

    private double valorSeguro(Atendimento atendimento) {
        return atendimento.getValor() == null ? 0 : atendimento.getValor();
    }
}
