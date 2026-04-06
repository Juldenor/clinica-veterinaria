package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.VeterinarioRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.VeterinarioResponseDTO;
import br.com.clinicavet.clinica.vet.model.Veterinario;
import br.com.clinicavet.clinica.vet.repositories.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository repository;

    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> cadastrar(@RequestBody VeterinarioRequestDTO data) {
        Veterinario vet = new Veterinario();
        vet.setNome(data.nome());
        vet.setEmail(data.email());
        vet.setSenha(data.senha());
        vet.setCrmv(data.crmv());
        repository.save(vet);
        return ResponseEntity.ok(new VeterinarioResponseDTO(vet));
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioResponseDTO>> listar() {
        var lista = repository.findAll().stream().map(VeterinarioResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}
