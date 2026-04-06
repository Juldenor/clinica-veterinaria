package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.AtendimentoRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.AtendimentoResponseDTO;
import br.com.clinicavet.clinica.vet.model.Atendimento;
import br.com.clinicavet.clinica.vet.model.Animal;
import br.com.clinicavet.clinica.vet.model.Veterinario;
import br.com.clinicavet.clinica.vet.model.StatusAtendimento;
import br.com.clinicavet.clinica.vet.repositories.AtendimentoRepository;
import br.com.clinicavet.clinica.vet.repositories.AnimalRepository;
import br.com.clinicavet.clinica.vet.repositories.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimentos")
@CrossOrigin(origins = "*")
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> cadastrar(@RequestBody AtendimentoRequestDTO data) {
        Animal animal = animalRepository.findById(data.animalId()).orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
        Veterinario vet = veterinarioRepository.findById(data.veterinarioId()).orElseThrow(() -> new RuntimeException("Veterinário não encontrado!"));
        
        Atendimento atendimento = new Atendimento();
        atendimento.setAnimal(animal);
        atendimento.setVeterinario(vet);
        atendimento.setDataAtendimento(data.dataAtendimento());
        atendimento.setDescricao(data.descricao());
        atendimento.setValor(data.valor());
        try {
            atendimento.setStatus(StatusAtendimento.valueOf(data.status()));
        } catch(Exception e) {
            atendimento.setStatus(StatusAtendimento.AGENDADO);
        }
        
        atendimentoRepository.save(atendimento);
        return ResponseEntity.ok(new AtendimentoResponseDTO(atendimento));
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> listar() {
        var lista = atendimentoRepository.findAll().stream().map(AtendimentoResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}
