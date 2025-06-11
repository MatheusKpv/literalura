package com.one.literalura.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.literalura.dto.GutendexLivroDTO;
import com.one.literalura.enums.IdiomasEnum;
import com.one.literalura.models.Autor;
import com.one.literalura.models.Livro;
import com.one.literalura.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorService autorService;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public LivroService(final LivroRepository livroRepository,
                        final AutorService autorService,
                        final HttpClient httpClient,
                        final ObjectMapper objectMapper) {
        this.livroRepository = livroRepository;
        this.autorService = autorService;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public void buscarESalvarLivrosPorTitulo(final String titulo) {
        try {
            final var livrosDTO = buscarLivrosDaAPI(titulo);

            for (final GutendexLivroDTO livroDTO : livrosDTO) {
                final var autor = localizarOuSalvarAutor(livroDTO);
                salvarLivroSeNaoExistir(livroDTO, autor);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar e salvar livros: " + e.getMessage(), e);
        }
    }

    public List<Livro> buscarLivrosEmDeterminadoIdioma(final IdiomasEnum idioma) {
        return livroRepository.findAllByIdioma(idioma.getAbreviacao());
    }

    private List<GutendexLivroDTO> buscarLivrosDaAPI(final String titulo) throws URISyntaxException, IOException, InterruptedException {
        final var urlBusca = "https://gutendex.com/books/?search=" + formatSearch(titulo);
        final var request = HttpRequest.newBuilder()
                .uri(new URI(urlBusca))
                .GET()
                .build();

        final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        final var resultsNode = objectMapper.readTree(response.body()).get("results");

        return objectMapper.readValue(resultsNode.toString(), new TypeReference<>() {
        });
    }

    private Autor localizarOuSalvarAutor(final GutendexLivroDTO livroDTO) {
        return autorService.buscarOuCadastrarAutor(livroDTO.autores().getFirst());
    }

    private void salvarLivroSeNaoExistir(final GutendexLivroDTO livroDTO, final Autor autor) {
        final var existeLivro = livroRepository.findById(livroDTO.id()).isPresent();

        if (!existeLivro) {
            final var novoLivro = new Livro(livroDTO, autor);
            livroRepository.save(novoLivro);
        }
    }

    private String formatSearch(final String nomeLivro) {
        return nomeLivro.replaceAll("\\s+", "+");
    }

    public List<Livro> buscarLivrosRegistrados() {
        return livroRepository.findAll();
    }
}
