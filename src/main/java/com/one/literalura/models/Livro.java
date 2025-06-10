package com.one.literalura.models;

import com.one.literalura.dto.GutendexLivroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    private String idioma;

    public Livro(GutendexLivroDTO livroDTO, Autor autor) {
        this.id = livroDTO.id();
        this.titulo = livroDTO.titulo();
        this.autor = autor;
        this.idioma = livroDTO.idiomas().getFirst();
    }

    @Override
    public String toString() {
        return "Livro {\n" +
                "  Título: " + titulo + "\n" +
                "  Idioma: " + idioma + "\n" +
                "  Autor: " + (Objects.nonNull(autor) ? autor.getNome() : "Desconhecido") +
                "\n}";
    }
}
