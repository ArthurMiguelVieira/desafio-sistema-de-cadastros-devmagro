package domain;
import java.io.*;
import java.sql.SQLOutput;

public class    FileWrite {
    public static void writeAnswers(String ... valuesToFile) {
        String fileName = valuesToFile[0].replace(" ","").toUpperCase();
        File userRegisterNumberFile = new File("src/files/UserRegisterNumber.txt");
        try (FileReader fr = new FileReader(userRegisterNumberFile)){

            char[] charsOfRegisterNumber = new char[(int)userRegisterNumberFile.length()];
            int i;
            int f = 0;
            while((i=fr.read()) != -1){
                charsOfRegisterNumber[f]=(char)i;
                f++;
            }
            String UserRegisterNumber = new String(charsOfRegisterNumber);
            try (FileWriter fw = new FileWriter(userRegisterNumberFile)){
                Integer valueOfUserRegisterNumber = Integer.valueOf(UserRegisterNumber);
                valueOfUserRegisterNumber++;
                System.out.println(valueOfUserRegisterNumber);
                fw.write(String.valueOf(valueOfUserRegisterNumber));
            }catch (IOException e){
                e.printStackTrace();
            }
            try(FileWriter fw = new FileWriter("src/files/users/"+UserRegisterNumber+"-"+fileName+".txt")){
                for(int x =0;x < valuesToFile.length; x++){
                    fw.write(valuesToFile[x]);
                    if(x < valuesToFile.length -1 ){
                        fw.write(",");
                    }

                }
                fw.write(",");
            }catch (IOException e){
                e.printStackTrace();
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public static void writeNewQuestion(String newQuestion){
        File file = new File("src/files/questions.txt");
        try (FileWriter fw = new FileWriter(file,true)){
            fw.write(newQuestion);
            fw.flush();
        } catch (IOException e) {
            System.out.println("Erro na Escrita das Questoes!");
            throw new RuntimeException(e);
        }

    }


    public static void deleteQuestion(int questionToDelete) {
        if(questionToDelete <= 3){
            System.out.println("Você só pode deletar as perguntas que você Criou!");
            return;
        }
        if(questionToDelete >= FileRead.amountOfQuestions()){
            System.out.println("pergunta não encontrada!");
        }
        File file = new File("src/files/questions.txt");

        try {
            // Contar o número de perguntas e armazenar em um vetor
            FileReader fr = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            fr.read(chars);
            fr.close();

            // Criar uma string a partir do vetor de caracteres
            String fileContent = new String(chars);
            String[] questions = fileContent.split("\\?");



            // Criar FileWriter para sobrescrever o arquivo
            FileWriter fw = new FileWriter(file);

            // Escrever no arquivo, excluindo a pergunta a ser removida
            for (int i = 0; i < questions.length; i++) {
                if (i != questionToDelete) {
                    fw.write(questions[i].trim());
                    if(i<questions.length-1){
                        fw.write("?");
                    }
                }
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
