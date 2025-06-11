package com.one.literalura.cli;

import com.one.literalura.dto.AutorLivrosDTO;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class AutorView {
    public String getMessageAutoresRegistrados(final List<AutorLivrosDTO> autoresDTO) {
        final var message = new StringBuilder();
        message.append("Autores registrados:").append("\n");
        autoresDTO.forEach(autorLivros -> message.append(autorLivros.toString()).append("\n"));

        return message.toString();
    }

    public String getMessageAutoresVivosEmDeterminadoAno(final List<AutorLivrosDTO> autoresDTO, final Year anoDeterminado) {
        final var message = new StringBuilder();
        message.append("Autores vivos em ").append(anoDeterminado.toString()).append(":").append("\n");
        autoresDTO.forEach(autor -> message.append(autor.toString()).append("\n"));

        return message.toString();
    }
}
