package domain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {

    public static String readQuestionsFile(int questionNumber, boolean returnAmountOfQuestions){
        File file = new File("src/files/questions.txt");
        try(FileReader fr = new FileReader(file)){

            char[] chars = new char[(int)file.length()];
            String result = "";

            int f = 0;
            int i;
            int amountOfQuestions = 0;

            while((i=fr.read())!= -1){
                chars[f] = (char)i;
                f++;

                if((char)i == '?'){
                    amountOfQuestions++;
                }
                result = new String(chars);
            }
            if(returnAmountOfQuestions == true){
                String convertToString = String.valueOf(amountOfQuestions);
                return convertToString;
            }
            int[] interrogationPosition = new int[amountOfQuestions];

            int i2 =0;
            for (int pos = 0;pos < result.length();pos++){
                if(result.charAt(pos)=='?'){
                    interrogationPosition[i2] = pos;
                    i2++;
                }
            }

            if (questionNumber >= 0 && questionNumber < amountOfQuestions) {
                int start = (questionNumber == 0) ? 0 : interrogationPosition[questionNumber - 1] + 1;
                int end = interrogationPosition[questionNumber]+1;
                return new StringBuilder(result).substring(start, end).trim();
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro na leitura das questões");
        }
        return "";
    }
    public static int amountOfQuestions(){
        Integer result = Integer.valueOf(readQuestionsFile(-1,true));
        return result;
    }
    public static String readAnswersFile(int client, int answer) {
        return readAnswersFile(client, answer, false);
    }

    public static String readAnswersFile(int client, int answer, boolean returnAmountOfAnswers) {
        File directory = new File("src/files/users");
        if (directory.listFiles() == null) {
            return "";
        }
        File[] files = directory.listFiles();

        // Verifica se o índice 'client' é válido
        if (client < 0 || client >= files.length) {
            System.out.println("Índice de cliente inválido: " + client);
            return "";
        }

        try (FileReader fr = new FileReader(files[client])) {
            StringBuilder resultBuilder = new StringBuilder();
            int readChar;
            while ((readChar = fr.read()) != -1) {
                resultBuilder.append((char) readChar);
            }

            String result = resultBuilder.toString();

            int[] answerCommaPosition = new int[amountOfQuestions()];
            int amountOfAnswer = 0;

            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == ',') {
                    if (amountOfAnswer < answerCommaPosition.length) {
                        answerCommaPosition[amountOfAnswer] = j;
                        amountOfAnswer++;
                    }
                }
            }

            if (returnAmountOfAnswers) {
                return String.valueOf(amountOfAnswer);
            }

            // Verifique o índice 'answer' antes de acessar
            if (answer >= 0 && answer < amountOfAnswer) {
                int start = (answer == 0) ? 0 : answerCommaPosition[answer - 1] + 1;
                int end = (answer < amountOfAnswer - 1) ? answerCommaPosition[answer] : result.length();
                return result.substring(start, end).trim();
            }

            return result.trim(); // Retorna a string completa se o índice não for válido
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na leitura das respostas");
        }
        return "";
    }

    public static int amountOfAnswers(int client){
            Integer result = Integer.valueOf(readAnswersFile(client,1,true));
            return result;
    }
    public static int amountOfUsers(){
        File directory = new File("src/files/users");
        File[] files = directory.listFiles();
        return files.length;
    }

    public static void searchUsers(String searchWord) {
        for (int i = 0; i < amountOfUsers(); i++) {
            String userFile = readAnswersFile(i, 0);
            Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(userFile);
            if (matcher.find()) {
                System.out.print("ID:" + (i+1) + " ");
                System.out.println(userFile);
            }
        }
    }

}
