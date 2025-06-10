package com.one.literalura.repositories;

import com.one.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeIgnoreCase(String nome);

    Set<Autor> findByAnoNascimentoLessThanEqualAndAnoDeMorteIsNull(Year ano);

    Set<Autor> findByAnoNascimentoLessThanEqualAndAnoDeMorteGreaterThanEqual(Year anoNascimento, Year anoMorte);

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> buscarAutoresComLivros();
}
