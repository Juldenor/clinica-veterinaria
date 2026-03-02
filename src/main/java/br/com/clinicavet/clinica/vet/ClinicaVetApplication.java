package br.com.clinicavet.clinica.vet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClinicaVetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaVetApplication.class, args);
	}

}
