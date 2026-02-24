package br.com.clinicavet.clinica.vet.repositories;

import br.com.clinicavet.clinica.vet.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
