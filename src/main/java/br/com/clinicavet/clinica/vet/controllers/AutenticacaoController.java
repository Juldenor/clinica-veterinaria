package br.com.clinicavet.clinica.vet.controllers;

import br.com.clinicavet.clinica.vet.dtos.AuthDTO;
import br.com.clinicavet.clinica.vet.dtos.LoginResponseDTO;
import br.com.clinicavet.clinica.vet.dtos.RegisterDTO;
import br.com.clinicavet.clinica.vet.model.Usuario;
import br.com.clinicavet.clinica.vet.security.TokenService;
import br.com.clinicavet.clinica.vet.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService; // Injetando o Service no lugar do Repository

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (Exception e) {
            // Se a senha for errada ou o usuário não existir, devolve um 400 Bad Request
            return ResponseEntity.badRequest().body("E-mail ou senha inválidos!");
        }
    }

    public record RecuperarSenhaDTO(String email) {}

    @PostMapping("/esqueci-senha")
    public ResponseEntity esqueciSenha(@RequestBody RecuperarSenhaDTO data) {
        try {
            usuarioService.recuperarSenha(data.email());
            // Retorna um JSON no formato {"sucesso": true, "mensagem": "..."}
            return ResponseEntity.ok(java.util.Map.of(
                    "sucesso", true,
                    "mensagem", "E-mail com a nova senha enviado com sucesso!"
            ));
        } catch (RuntimeException e) {
            // Retorna o erro no formato {"sucesso": false, "mensagem": "..."}
            return ResponseEntity.badRequest().body(java.util.Map.of(
                    "sucesso", false,
                    "mensagem", e.getMessage()
            ));
        }
    }
    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody RegisterDTO data) {
        try {
            // Repassa a responsabilidade para o Service
            usuarioService.registrarUsuario(data);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {
            // Se o Service lançar o erro de "e-mail já cadastrado", o Controller devolve 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}