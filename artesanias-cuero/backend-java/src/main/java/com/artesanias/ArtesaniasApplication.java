package com.artesanias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // ← Esto escanea todos los subpaquetes
public class ArtesaniasApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArtesaniasApplication.class, args);
    }
}