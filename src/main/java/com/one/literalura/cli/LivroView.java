package com.one.literalura.cli;

import com.one.literalura.dto.AutorLivrosDTO;
import com.one.literalura.enums.IdiomasEnum;
import com.one.literalura.enums.OpcoesEnum;
import com.one.literalura.models.Livro;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class LivroView {
    public String getMessageOpcoes() {
        final var message = new StringBuilder();
        message.append("escolha o número de sua opção:").append("\n");

        for (OpcoesEnum opcao : OpcoesEnum.values()) {
            message.append(opcao.getNumber()).append(" - ").append(opcao.getDescription()).append("\n");
        }

        message.append("0 - Sair").append("\n");

        return message.toString();
    }

    public void getMessageLivrosSalvos() {
        System.out.println("Livros salvos com sucesso!");
    }

    public void getMessageLivrosRegistrados(final List<Livro> livros) {
        final var message = new StringBuilder();
        message.append("Livros registrados:").append("\n");
        livros.forEach(livro -> message.append(livro.toString()).append("\n"));

        System.out.println(message);
    }

    public void getMessageAutoresRegistrados(final List<AutorLivrosDTO> autoresDTO) {
        final var message = new StringBuilder();
        message.append("Autores registrados:").append("\n");
        autoresDTO.forEach(autorLivros -> message.append(autorLivros.toString()).append("\n"));

        System.out.println(message);
    }

    public String getMessageOpcoesIdiomas() {
        final var message = new StringBuilder();
        message.append("escolha o número de sua opção:").append("\n");

        for (IdiomasEnum opcao : IdiomasEnum.values()) {
            message.append(opcao.getAbreviacao()).append(" - ").append(opcao.getDescription()).append("\n");
        }

        return message.toString();
    }

    public void getMessageLivrosEmDeterminadoIdioma(final List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro neste idioma encontrado!");
        } else {
            System.out.printf("%s livros neste idioma encontrado!\n", livros.size());
        }
    }

    public void getMessageAutoresVivosEmDeterminadoAno(final List<AutorLivrosDTO> autoresDTO, final Year anoDeterminado) {
        final var message = new StringBuilder();
        message.append("Autores vivos em ").append(anoDeterminado.toString()).append(":").append("\n");
        autoresDTO.forEach(autor -> message.append(autor.toString()).append("\n"));

        System.out.println(message);
    }
}
