package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.ClienteRequestDTO;
import br.com.clinicavet.clinica.vet.dtos.ClienteResponseDTO;
import br.com.clinicavet.clinica.vet.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO clienteCriado = clienteService.criarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }
}