package br.com.clinicavet.clinica.vet.repositories;

import br.com.clinicavet.clinica.vet.model.Cliente;
import br.com.clinicavet.clinica.vet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}