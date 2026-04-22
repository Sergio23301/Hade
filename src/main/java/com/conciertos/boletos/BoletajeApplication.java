package com.conciertos.boletos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoletajeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoletajeApplication.class, args);
        System.out.println(" SERVICIO DE BOLETAJE INICIADO CORRECTAMENTE ");
    }
}