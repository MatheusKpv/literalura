package com.one.literalura.dto;

import java.time.Year;
import java.util.List;
import java.util.Objects;

public record AutorLivrosDTO(String nomeAutor, Year anoNascimento, Year anoDeMorte, List<String> tituloLivros) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Autor {\n");
        sb.append("  Nome: ").append(nomeAutor).append("\n");
        sb.append("  Ano de Nascimento: ").append(Objects.nonNull(anoNascimento) ? anoNascimento : "Desconhecido").append("\n");
        sb.append("  Ano de Morte: ").append(Objects.nonNull(anoDeMorte) ? anoDeMorte : "Ainda vivo").append("\n");

        sb.append("  Livros: ");
        if (Objects.nonNull(tituloLivros) && !tituloLivros.isEmpty()) {
            tituloLivros.forEach(titulo -> sb.append("\n    - ").append(titulo));
        } else {
            sb.append("Nenhum livro");
        }

        sb.append("\n}");
        return sb.toString();
    }
}
