package com.one.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexLivroDTO(
        Long id,
        @JsonProperty("title") String titulo,
        @JsonProperty("languages") List<String> idiomas,
        @JsonProperty("authors") List<GutendexAutorDTO> autores
) {
}
