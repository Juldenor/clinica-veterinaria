package br.com.clinicavet.clinica.vet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    private LocalDate dataAtendimento;

    private String cidade;

    private String estado;

    private String descricao;
    private Double valor;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento status;
}
