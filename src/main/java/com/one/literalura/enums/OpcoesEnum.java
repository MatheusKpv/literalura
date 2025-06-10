package com.one.literalura.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpcoesEnum {
    BUSCAR_LIVRO_POR_TITULO(1, "buscar livro por título"),
    LISTAR_LIVROS_REGISTRADOS(2, "listar livros registrados"),
    LISTAR_AUTORES_REGISTRADOS(3, "listar autores registrados"),
    LISTAR_AUTORES_VIVOS_EM_DETERMINADO_ANO(4, "listar autores vivos em um determinado ano"),
    LISTAR_LIVROS_EM_DETERMINADO_IDIOMA(5, "listar livros em um determinado idioma");

    final int number;
    final String description;

    public static OpcoesEnum getOpcao(final int number) {
        for (OpcoesEnum opcao : OpcoesEnum.values()) {
            if (opcao.getNumber() == number) {
                return opcao;
            }
        }

        return null;
    }
}
