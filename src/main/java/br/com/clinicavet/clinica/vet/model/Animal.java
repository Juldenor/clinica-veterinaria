package br.com.clinicavet.clinica.vet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String sexo;
    private String raca;
    private String cor;
    private String pelagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;
}
