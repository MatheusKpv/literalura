package com.one.literalura.controllers;

import com.one.literalura.cli.LivroView;
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

    public LivroController(LivroService livroService, AutorService autorService, LivroView livroView) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.livroView = livroView;
    }

    public void iniciar() {
        var numOpcao = 0;

        do {
            numOpcao = lerInteger(livroView.getMessageOpcoes());

            final var opcao = OpcoesEnum.getOpcao(numOpcao);
            if (opcao != null) {
                switch (opcao) {
                    case BUSCAR_LIVRO_POR_TITULO -> {
                        final var titulo = lerString("Insira o nome do livro que você deseja procurar: ");
                        livroService.buscarLivroPorTitulo(titulo);
                        livroView.getMessageLivrosSalvos();
                    }
                    case LISTAR_LIVROS_REGISTRADOS -> {
                        livroView.getMessageLivrosRegistrados(livroService.buscarLivrosRegistrados());
                    }
                    case LISTAR_AUTORES_REGISTRADOS -> {
                        livroView.getMessageAutoresRegistrados(autorService.buscarAutoresRegistrados());
                    }
                    case LISTAR_AUTORES_VIVOS_EM_DETERMINADO_ANO -> {
                        final var ano = Year.of(lerInteger("Insira o ano desejado: "));
                        livroView.getMessageAutoresVivosEmDeterminadoAno(autorService.buscarAutoresVivosEmDeterminadoAno(ano), ano);
                    }
                    case LISTAR_LIVROS_EM_DETERMINADO_IDIOMA -> {
                        IdiomasEnum idioma;
                        do {
                            idioma = IdiomasEnum.getOpcaoByAbreviacao(lerString(livroView.getMessageOpcoesIdiomas()));
                        } while (Objects.isNull(idioma));

                        livroView.getMessageLivrosEmDeterminadoIdioma(livroService.buscarLivrosEmDeterminadoIdioma(idioma));
                    }
                }
            }

        } while (numOpcao != 0);
    }
}
