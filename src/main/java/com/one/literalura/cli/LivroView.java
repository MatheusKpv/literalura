package com.one.literalura.cli;

import com.one.literalura.models.Livro;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.one.literalura.util.InputHelper.lerString;

@Component
public class LivroView {
    public void getMessageLivrosSalvos() {
        System.out.println("Livros salvos com sucesso!");
    }

    public void mostrarLivros(final List<Livro> livros) {
        final var message = new StringBuilder();
        message.append("Livros registrados:").append("\n");
        livros.forEach(livro -> message.append(livro.toString()).append("\n"));

        System.out.println(message);
    }

    public void mostrarLivrosPorIdioma(final List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro neste idioma encontrado!");
        } else {
            System.out.printf("%s livros neste idioma encontrado!\n", livros.size());
        }
    }

    public String lerTitulo() {
        return lerString("Insira o nome do livro que você deseja procurar: ");
    }
}
