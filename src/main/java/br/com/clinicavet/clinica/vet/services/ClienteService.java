package br.com.clinicavet.clinica.vet.services;

import br.com.clinicavet.clinica.vet.dtos.ClienteRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.ClienteResponseDTO;
import br.com.clinicavet.clinica.vet.model.Cliente;
import br.com.clinicavet.clinica.vet.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper; // Injetando o ModelMapper aqui!

    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        // 1. Converte o DTO para Entidade magicamente
        Cliente cliente = modelMapper.map(dto, Cliente.class);

        // 2. Salva no banco
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // 3. Converte a Entidade salva de volta para DTO
        return modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
    }
}