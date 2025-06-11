package com.one.literalura.cli;

import com.one.literalura.enums.IdiomasEnum;
import org.springframework.stereotype.Component;

@Component
public class IdiomaView {
    public String getMessageOpcoesIdiomas() {
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
}
