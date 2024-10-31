package domain;

import java.util.Scanner;

public class User {

    public static void listAllUsers(){
        System.out.println();
        System.out.println("Lista Todos Usuarios");
        for (int i = 0; i < FileRead.amountOfUsers(); i++) {
            System.out.print(i+1+"- ");
            System.out.print(FileRead.readAnswersFile(i,0)+" ");
            System.out.println();
        }
    }
    public static void register(Scanner scanner) {
        String[] answers = new String[FileRead.amountOfQuestions()];

        for (int i = 0; i < FileRead.amountOfQuestions(); i++) {
            System.out.println(FileRead.readQuestionsFile(i, false));
            String answer = scanner.nextLine();  // Use o scanner passado como parâmetro

            boolean valid = false;  // Flag para controle de validação

            while (!valid) {
                try {
                    switch (i) {
                        case 0:
                            validateName(answer);
                            break;
                        case 1:
                            validateEmail(answer, answers);
                            break;
                        case 2:
                            validateAge(answer);
                            break;
                        case 3:
                            validateHeight(answer);
                            break;
                        default:
                            break;
                    }
                    answers[i] = answer;  // Armazena a resposta após a validação
                    valid = true;  // Se tudo estiver correto, sai do loop
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.print("Por favor, tente novamente: ");
                    answer = scanner.nextLine();  // Lê uma nova entrada
                }
            }
        }

        FileWrite.writeAnswers(answers);
        for (String answer : answers) {
            System.out.println(answer);
        }
    }


    private static void validateName(String name) {
        if (name.length() < 10) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 10 caracteres.");
        }
    }

    private static void validateEmail(String email, String[] answers) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("O e-mail deve conter o caractere '@'.");
        }
        for (int i = 0; i < FileRead.amountOfUsers(); i++) {
            if (FileRead.readAnswersFile(i,1).equals(email)) {
                throw new IllegalArgumentException("Este e-mail já foi cadastrado.");
            }
        }
    }

    private static void validateAge(String ageString) {
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A idade deve ser um número válido.");
        }
        if (age <= 18) {
            throw new IllegalArgumentException("A idade deve ser maior que 18 anos.");
        }
    }

    private static void validateHeight(String height) {
        if (!height.matches("\\d+[,]\\d+")) {
            throw new IllegalArgumentException("A altura deve ser um número no formato 'X,Y' (ex: 1,75).");
        }
    }
}

