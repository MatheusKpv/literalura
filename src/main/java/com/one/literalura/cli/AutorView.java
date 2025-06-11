package com.one.literalura.cli;

import com.one.literalura.dto.AutorLivrosDTO;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

import static com.one.literalura.cli.ConsoleInput.lerInteger;

@Component
public class AutorView {
    public void mostrarAutores(final List<AutorLivrosDTO> autoresDTO) {
        final var message = new StringBuilder();
        message.append("Autores registrados:").append("\n");
        autoresDTO.forEach(autorLivros -> message.append(autorLivros.toString()).append("\n"));

        System.out.println(message);
    }

    public void mostrarAutoresVivos(final List<AutorLivrosDTO> autoresDTO, final Year anoDeterminado) {
        if (!autoresDTO.isEmpty()) {
            final var message = new StringBuilder();
            message.append("Autores vivos em ").append(anoDeterminado.toString()).append(":").append("\n");
            autoresDTO.forEach(autor -> message.append(autor.toString()).append("\n"));

            System.out.println(message);
        } else {
            System.out.println("Nenhum autor vivo encontrado em " + anoDeterminado);
        }
    }

    public Year lerAno() {
        return Year.of(lerInteger("Insira o ano desejado: "));
    }
}
