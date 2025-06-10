package com.one.literalura.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdiomasEnum {
    EN("en", "Inglês"),
    FR("fr", "Francês");

    final String abreviacao;
    final String description;

    public static IdiomasEnum getOpcaoByAbreviacao(final String abreviacao) {
        for (IdiomasEnum opcao : IdiomasEnum.values()) {
            if (abreviacao.equals(opcao.getAbreviacao())) {
                return opcao;
            }
        }

        return null;
    }
}
