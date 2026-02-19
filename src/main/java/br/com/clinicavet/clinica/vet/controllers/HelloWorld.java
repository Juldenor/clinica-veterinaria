package br.com.clinicavet.clinica.vet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorld {


    @GetMapping
    public String hello(){

        return "OIIIII";
    }


}
