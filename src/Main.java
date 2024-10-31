import domain.FileRead;
import domain.FileWrite;
import domain.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Cadastrar o usuário\n" +
                    "2 - Listar todos usuários cadastrados\n" +
                    "3 - Cadastrar nova pergunta no formulário\n" +
                    "4 - Deletar pergunta do formulário\n" +
                    "5 - Pesquisar usuário por nome, idade ou email\n" +
                    "6 - Sair");

            System.out.print("Escolha uma opção: ");
            int answer;

            try {
                // Verifica se a próxima entrada é um inteiro
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    scanner.nextLine();  // Limpa o buffer após `nextInt`
                } else {
                    System.out.println("Por favor, insira um número válido.");
                    scanner.next();  // Limpa a entrada inválida
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler a entrada. Tente novamente.");
                scanner.nextLine();  // Limpa o buffer em caso de erro
                continue;
            }

            switch (answer) {
                case 1:
                    System.out.println();
                    User.register(scanner);
                    break;
                case 2:
                    System.out.println();
                    User.listAllUsers();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Digite a Pergunta que deseja adicionar, obs: com um ? no final!!!");
                    String perguntaAdicionar = scanner.nextLine();
                    FileWrite.writeNewQuestion(perguntaAdicionar);
                    System.out.println("Pergunta adicionada com sucesso!");
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Escreva o número da pergunta a deletar!");
                    System.out.println("Você poderá deletar apenas perguntas que você mesmo criou!!");
                    for (int i = 0; i < FileRead.amountOfQuestions(); i++) {
                        System.out.print(i + " - ");
                        System.out.println(FileRead.readQuestionsFile(i, false));
                    }
                    try {
                        if (scanner.hasNextInt()) {
                            int perguntaDeletar = scanner.nextInt();
                            scanner.nextLine();  // Limpa o buffer após `nextInt`
                            FileWrite.deleteQuestion(perguntaDeletar);
                            System.out.println("Pergunta deletada com sucesso!");
                        } else {
                            System.out.println("Número inválido. Tente novamente.");
                            scanner.next();  // Limpa a entrada inválida
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao deletar a pergunta. Tente novamente.");
                        scanner.nextLine();  // Limpa o buffer em caso de erro
                    }
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Escreva o nome do usuário a pesquisar! ");
                    String nomeUsuarioPesquisa = scanner.nextLine();
                    FileRead.searchUsers(nomeUsuarioPesquisa);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;  // Encerra o programa
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            System.out.println(); // Para uma melhor formatação na saída
        }
    }
}
