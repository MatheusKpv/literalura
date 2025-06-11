package com.one.literalura.controllers;

import com.one.literalura.cli.AutorView;
import com.one.literalura.cli.IdiomaView;
import com.one.literalura.cli.LivroView;
import com.one.literalura.cli.MenuView;
import com.one.literalura.enums.IdiomasEnum;
import com.one.literalura.enums.OpcoesEnum;
import com.one.literalura.services.AutorService;
import com.one.literalura.services.LivroService;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Objects;

import static com.one.literalura.util.InputHelper.lerInteger;
import static com.one.literalura.util.InputHelper.lerString;

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
                        livroService.buscarESalvarLivrosPorTitulo(titulo);
                        livroView.getMessageLivrosSalvos();
                    }
                    case LISTAR_LIVROS_REGISTRADOS -> {
                        livroView.getMessageLivrosRegistrados(livroService.buscarLivrosRegistrados());
                    }
                    case LISTAR_AUTORES_REGISTRADOS -> {
                        autorView.getMessageAutoresRegistrados(autorService.buscarAutoresRegistrados());
                    }
                    case LISTAR_AUTORES_VIVOS_EM_DETERMINADO_ANO -> {
                        final var ano = Year.of(lerInteger("Insira o ano desejado: "));
                        autorView.getMessageAutoresVivosEmDeterminadoAno(autorService.buscarAutoresVivosEmDeterminadoAno(ano), ano);
                    }
                    case LISTAR_LIVROS_EM_DETERMINADO_IDIOMA -> {
                        IdiomasEnum idioma;
                        do {
                            idioma = IdiomasEnum.getOpcaoByAbreviacao(lerString(idiomaView.getMessageOpcoesIdiomas()));
                        } while (Objects.isNull(idioma));

                        livroView.getMessageLivrosEmDeterminadoIdioma(livroService.buscarLivrosEmDeterminadoIdioma(idioma));
                    }
                }
            }

        } while (numOpcao != 0);
    }
}
