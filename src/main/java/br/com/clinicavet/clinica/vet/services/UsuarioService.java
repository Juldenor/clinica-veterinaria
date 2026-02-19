package br.com.clinicavet.clinica.vet.services;

import br.com.clinicavet.clinica.vet.model.Cliente;
import br.com.clinicavet.clinica.vet.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public List<Cliente> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Cliente criarUsuario(Cliente cliente) {

        return usuarioRepository.save(cliente);
    }
}
