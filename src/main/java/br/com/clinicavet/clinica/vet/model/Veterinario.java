package br.com.clinicavet.clinica.vet.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String crmv;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;
}
