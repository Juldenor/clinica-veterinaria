package br.com.clinicavet.clinica.vet.services;

import br.com.clinicavet.clinica.vet.dtos.AnimalRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.AnimalResponseDTO;
import br.com.clinicavet.clinica.vet.model.Animal;
import br.com.clinicavet.clinica.vet.model.Cliente;
import br.com.clinicavet.clinica.vet.repositories.AnimalRepository;
import br.com.clinicavet.clinica.vet.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public AnimalResponseDTO cadastrar(AnimalRequestDTO data) {
        // 1. Busca o cliente (dono) no banco
        Cliente dono = clienteRepository.findById(data.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // 2. Monta o animal
        Animal animal = new Animal();
        animal.setNome(data.nome());
        animal.setIdade(data.idade());
        animal.setSexo(data.sexo());
        animal.setRaca(data.raca());
        animal.setCor(data.cor());
        animal.setPelagem(data.pelagem());
        animal.setCliente(dono); // Amarra o animal ao dono!

        // 3. Salva no banco
        Animal animalSalvo = animalRepository.save(animal);

        // 4. Retorna formatado no DTO de resposta
        return new AnimalResponseDTO(animalSalvo);
    }

    public List<AnimalResponseDTO> listarTodos() {
        return animalRepository.findAll().stream().map(AnimalResponseDTO::new).toList();
    }
}