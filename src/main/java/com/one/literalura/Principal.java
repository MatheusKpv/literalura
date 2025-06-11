package com.one.literalura;

import com.one.literalura.controllers.LivroController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Principal implements CommandLineRunner {
    private final LivroController livroController;

    public Principal(final LivroController livroController) {
        this.livroController = livroController;
    }

    @Override
    public void run(String... args) {
        this.livroController.iniciar();
    }
}
