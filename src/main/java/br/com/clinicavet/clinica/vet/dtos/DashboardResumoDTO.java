package br.com.clinicavet.clinica.vet.dtos;

import java.util.List;

public record DashboardResumoDTO(
        long totalAnimais,
        long totalClientes,
        long totalVeterinarios,
        long totalAtendimentos,
        long atendimentosAgendados,
        long atendimentosConcluidos,
        long atendimentosCancelados,
        long atendimentosAtrasados,
        long proximos7Dias,
        double receitaRealizada,
        double receitaPrevista,
        double ticketMedio,
        double taxaConclusao,
        List<AtendimentoResponseDTO> proximosAtendimentos,
        List<AtendimentoResponseDTO> atrasados
) {}
