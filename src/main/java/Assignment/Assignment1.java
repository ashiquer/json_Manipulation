package Assignment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assignment1 {
    public static void WriteJSONQues(String fileName, int numberOfQuestions) throws IOException, ParseException {
        char ch;
        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject quesObj = new JSONObject();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter question number " + numberOfQuestions + ":");
            quesObj.put("Question", scanner.nextLine());
            System.out.println("Enter 1st option: ");
            quesObj.put("Option-a", scanner.nextLine());
            System.out.println("Enter 2nd option: ");
            quesObj.put("Option-b", scanner.nextLine());
            System.out.println("Enter 3rd option: ");
            quesObj.put("Option-c", scanner.nextLine());
            System.out.println("Enter 4th option: ");
            quesObj.put("Option-d", scanner.nextLine());
            System.out.println("Answer: ");
            quesObj.put("Answer", scanner.nextLine());

            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(quesObj);
            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
            System.out.println("Saved!");
            System.out.println(jsonArray);
            System.out.println("\nDo you want to add more questions?[y/n]");
            ch = scanner.next().charAt(0);
            numberOfQuestions++;
        } while (ch != 'n');
    }

    public static void ReadJSONQues(String fileName, int max, int questionsToBeAnswered, int min, int answerCounter) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        max = jsonArray.size();
        int i = 1;
        while (i <= questionsToBeAnswered) {
            int random = (int) (Math.random() * ((max - min) + min));
            JSONObject json = (JSONObject) jsonArray.get(random);
            String question = (String) json.get("Question");
            String option1 = (String) json.get("Option-a");
            String option2 = (String) json.get("Option-b");
            String option3 = (String) json.get("Option-c");
            String option4 = (String) json.get("Option-d");
            String  answer = (String) json.get("Answer");
            System.out.println("Question: " + question);
            System.out.println("Option - a: " + option1);
            System.out.println("Option - b: " + option2);
            System.out.println("Option - c: " + option3);
            System.out.println("Option - d: " + option4);
            System.out.println("Input your choice. a/b/c/d?");
            Scanner scanner = new Scanner(System.in);
            String  choice = scanner.next();
            if (choice.equals(answer)) {
                System.out.println("Your answer is correct!\n");
                answerCounter++;
            } else {
                System.out.println("Your answer is wrong!\n");
            }
            i++;
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        String fileName = "quiz\\QuestionBank.json";
        int numberOfQuestions = 1;
        int answerCounter = 0;
        int min = 0;
        int max = 0;
        int questionsToBeAnswered = 5;

        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Start Quiz\n2.Add Quiz");
        System.out.print("Select one: ");;
        int readWriteChoice = scanner.nextInt();

        switch (readWriteChoice){
            case 1:
                ReadJSONQues(fileName, max, questionsToBeAnswered, min, answerCounter);
                System.out.println("You have gotten " + answerCounter + " out of " + questionsToBeAnswered + ".");
                break;
            case 2:
                WriteJSONQues(fileName, numberOfQuestions);
                break;
            default:
                System.exit(0);
        }
    }
}
