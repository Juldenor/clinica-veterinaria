package br.com.clinicavet.clinica.vet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Animal> animais;

}

