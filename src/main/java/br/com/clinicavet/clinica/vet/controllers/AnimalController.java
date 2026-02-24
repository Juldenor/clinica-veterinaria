package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.AnimalRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.AnimalResponseDTO;
import br.com.clinicavet.clinica.vet.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> cadastrar(@RequestBody AnimalRequestDTO data) {
        AnimalResponseDTO animalSalvo = animalService.cadastrar(data);
        return ResponseEntity.ok(animalSalvo);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> listar() {
        return ResponseEntity.ok(animalService.listarTodos());
    }
}