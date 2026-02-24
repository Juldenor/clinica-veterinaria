package br.com.clinicavet.clinica.vet.services;

import br.com.clinicavet.clinica.vet.dtos.RegisterDTO;
import br.com.clinicavet.clinica.vet.model.Usuario;
import br.com.clinicavet.clinica.vet.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService;

    public Usuario registrarUsuario(RegisterDTO data) {
        // Regra de negócio: Verifica se o e-mail já existe
        if (repository.findByEmail(data.email()) != null) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema.");
        }

        // Criptografa a senha
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());

        // Monta a entidade
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(data.email());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(data.role());

        // Salva no banco
        return repository.save(novoUsuario);
    }

    public void recuperarSenha(String email) {
        Usuario usuario = repository.findByEmail(email);

        if (usuario == null) {
            throw new RuntimeException("E-mail não encontrado no sistema.");
        }

        // 1. Gera uma senha provisória de 8 caracteres
        String novaSenha = java.util.UUID.randomUUID().toString().substring(0, 8);

        // 2. Criptografa e salva a nova senha no banco
        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        repository.save(usuario);

        // 3. Monta e envia o e-mail
        String assunto = "Clínica Veterinária - Recuperação de Senha";
        String mensagem = "Olá!\n\nSua nova senha de acesso é: " + novaSenha +
                "\n\nRecomendamos que você altere esta senha assim que fizer o login.";

        emailService.enviarEmailTexto(usuario.getEmail(), assunto, mensagem);
    }
}