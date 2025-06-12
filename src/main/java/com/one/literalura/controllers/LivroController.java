package com.one.literalura.controllers;

import com.one.literalura.cli.AutorView;
import com.one.literalura.cli.IdiomaView;
import com.one.literalura.cli.LivroView;
import com.one.literalura.cli.MenuView;
import com.one.literalura.enums.OpcoesEnum;
import com.one.literalura.services.AutorService;
import com.one.literalura.services.LivroService;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.one.literalura.cli.ConsoleInput.lerInteger;

@Component
public class LivroController {
    private final LivroService livroService;
    private final AutorService autorService;
    private final LivroView livroView;
    private final AutorView autorView;
    private final IdiomaView idiomaView;
    private final MenuView menuView;

    public LivroController(final LivroService livroService,
                           final AutorService autorService,
                           final LivroView livroView,
                           final AutorView autorView,
                           final IdiomaView idiomaView,
                           final MenuView menuView) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.livroView = livroView;
        this.autorView = autorView;
        this.idiomaView = idiomaView;
        this.menuView = menuView;
    }

    public void iniciar() {
        var numOpcao = 0;

        do {
            numOpcao = lerInteger(menuView.getMessageOpcoes());
            final var opcao = OpcoesEnum.getOpcao(numOpcao);

            if (Objects.nonNull(opcao)) {
                switch (opcao) {
                    case BUSCAR_LIVRO_POR_TITULO -> {
                        final var titulo = livroView.lerTitulo();
                        final var livros = livroService.buscarESalvarLivrosPorTitulo(titulo);
                        livroView.mostrarLivros(livros);
                    }
                    case LISTAR_LIVROS_REGISTRADOS -> {
                        final var livros = livroService.buscarLivrosRegistrados();
                        livroView.mostrarLivros(livros);
                    }
                    case LISTAR_AUTORES_REGISTRADOS -> {
                        final var autor =  autorService.buscarAutoresRegistrados();
                        autorView.mostrarAutores(autor);
                    }
                    case LISTAR_AUTORES_VIVOS_EM_DETERMINADO_ANO -> {
                        final var ano = autorView.lerAno();
                        final var autoresVivos = autorService.buscarAutoresVivosEmDeterminadoAno(ano);
                        autorView.mostrarAutoresVivos(autoresVivos, ano);
                    }
                    case LISTAR_LIVROS_EM_DETERMINADO_IDIOMA -> {
                        final var idioma = idiomaView.lerIdioma();
                        final var livrosPorIdioma = livroService.buscarLivrosEmDeterminadoIdioma(idioma);
                        livroView.mostrarLivrosPorIdioma(livrosPorIdioma);
                    }
                }
            }

        } while (numOpcao != 0);

        System.exit(0);
    }
}
