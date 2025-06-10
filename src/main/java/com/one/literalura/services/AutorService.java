package com.one.literalura.services;

import com.one.literalura.dto.AutorLivrosDTO;
import com.one.literalura.models.Autor;
import com.one.literalura.models.Livro;
import com.one.literalura.repositories.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorLivrosDTO> buscarAutoresRegistrados() {
        return converterAutoresParaDTO(autorRepository.buscarAutoresComLivros());
    }

    @Transactional(readOnly = true)
    public List<AutorLivrosDTO> buscarAutoresVivosEmDeterminadoAno(final Year ano) {
        final var autoresVivosSemFalecimento = autorRepository.findByAnoNascimentoLessThanEqualAndAnoDeMorteIsNull(ano);
        final var autoresVivosComFalecimento = autorRepository.findByAnoNascimentoLessThanEqualAndAnoDeMorteGreaterThanEqual(ano, ano);

        autoresVivosComFalecimento.addAll(autoresVivosSemFalecimento);
        return converterAutoresParaDTO(autoresVivosComFalecimento.stream().toList());
    }

    private List<AutorLivrosDTO> converterAutoresParaDTO(final List<Autor> autores) {
        return autores.stream()
                .map(autor -> new AutorLivrosDTO(
                        autor.getNome(),
                        autor.getAnoNascimento(),
                        autor.getAnoDeMorte(),
                        autor.getLivros().stream()
                                .map(Livro::getTitulo)
                                .toList()
                ))
                .toList();
    }
}
