package com.one.literalura.cli;

import com.one.literalura.enums.IdiomasEnum;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.one.literalura.util.InputHelper.lerString;

@Component
public class IdiomaView {
    public String getMensagemOpcoesIdiomas() {
        final var message = new StringBuilder();
        message.append("escolha o número de sua opção:").append("\n");

        for (IdiomasEnum opcao : IdiomasEnum.values()) {
            message.append(opcao.getAbreviacao())
                    .append(" - ")
                    .append(opcao.getDescription())
                    .append("\n");
        }

        return message.toString();
    }

    public IdiomasEnum lerIdioma() {
        IdiomasEnum idioma;
        do {
            final String input = lerString(getMensagemOpcoesIdiomas());
            idioma = IdiomasEnum.getOpcaoByAbreviacao(input.trim());

            if (idioma == null) {
                System.out.println("Opção inválida. Por favor, tente novamente.\n");
            }
        } while (Objects.isNull(idioma));

        return idioma;
    }
}
