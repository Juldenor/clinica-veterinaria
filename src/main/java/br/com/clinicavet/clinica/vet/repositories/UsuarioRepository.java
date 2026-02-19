package br.com.clinicavet.clinica.vet.repositories;

import br.com.clinicavet.clinica.vet.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Cliente, Long> {
}
