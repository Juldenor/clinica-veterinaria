package br.com.clinicavet.clinica.vet.repositories;

import br.com.clinicavet.clinica.vet.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}
