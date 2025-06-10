package com.one.literalura.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.literalura.dto.GutendexLivroDTO;
import com.one.literalura.enums.IdiomasEnum;
import com.one.literalura.models.Autor;
import com.one.literalura.models.Livro;
import com.one.literalura.repositories.AutorRepository;
import com.one.literalura.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivroPorTitulo(String nomeLivro) {
        try (var client = HttpClient.newHttpClient()) {

            final var request = HttpRequest.newBuilder()
                    .uri(new URI("https://gutendex.com/books/?search=".concat(formatSearch(nomeLivro))))
                    .GET()
                    .build();

            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            final var mapper = new ObjectMapper();
            final var results = mapper.readTree(response.body()).get("results");
            final var livrosDTO = mapper.readValue(results.toString(), new TypeReference<List<GutendexLivroDTO>>() {
            });

            livrosDTO.forEach(livro -> {
                final var autor = autorRepository.findByNomeIgnoreCase(livro.autores().getFirst().nome()).orElseGet(() -> {
                    final var autorDTO = livro.autores().getFirst();
                    final var autorToSave = Autor.builder()
                            .nome(autorDTO.nome())
                            .anoNascimento(Year.of(autorDTO.anoNascimento()))
                            .anoDeMorte(Year.of(autorDTO.anoDeMorte()))
                            .build();
                    return autorRepository.save(autorToSave);
                });
                livroRepository.findById(livro.id()).orElseGet(() -> livroRepository.save(new Livro(livro, autor)));
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String formatSearch(final String nomeLivro) {
        return nomeLivro.replaceAll("\\s+", "+");
    }

    public List<Livro> buscarLivrosRegistrados() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarLivrosEmDeterminadoIdioma(final IdiomasEnum idioma) {
        return livroRepository.findAllByIdioma(idioma.getAbreviacao());
    }
}
