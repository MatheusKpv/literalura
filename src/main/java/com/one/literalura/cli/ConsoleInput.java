package com.one.literalura.cli;

import java.util.Scanner;

public class ConsoleInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerString(final String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static Integer lerInteger(final String mensagem) {
        while (true) {
            System.out.print(mensagem);

            final var entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (Exception e) {
                System.out.println("Valor inválido. Por favor, digite um número válido. Exemplo: 2\n");
            }
        }
    }
}
