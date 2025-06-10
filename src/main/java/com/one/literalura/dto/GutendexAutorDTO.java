package com.one.literalura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GutendexAutorDTO(
        @JsonProperty("name") String nome,
        @JsonProperty("birth_year") Integer anoNascimento,
        @JsonProperty("death_year") Integer anoDeMorte
) {
}
