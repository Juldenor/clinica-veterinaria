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
    private ModelMapper modelMapper;

    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF.");
        }

        Cliente cliente = modelMapper.map(dto, Cliente.class);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return modelMapper.map(clienteSalvo, ClienteResponseDTO.class);
    }
}