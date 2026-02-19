package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.model.Cliente;
import br.com.clinicavet.clinica.vet.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping()
    public List<Cliente> retornarUsuarios(){

        return usuarioService.listarUsuarios();
    }

    @PostMapping()
    public Cliente criarUsuario(@RequestBody Cliente cliente){
        return usuarioService.criarUsuario(cliente);

    }
}
