package com.one.literalura.cli;

import com.one.literalura.enums.OpcoesEnum;
import org.springframework.stereotype.Component;

@Component
public class MenuView {
    public String getMessageOpcoes() {
        final var message = new StringBuilder();
        message.append("escolha o número de sua opção:").append("\n");

        for (OpcoesEnum opcao : OpcoesEnum.values()) {
            message.append(opcao.getNumber()).append(" - ").append(opcao.getDescription()).append("\n");
        }

        message.append("0 - Sair").append("\n");

        return message.toString();
    }
}
