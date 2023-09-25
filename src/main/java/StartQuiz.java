import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StartQuiz {
    public static void startQuiz() throws IOException, ParseException {
        String fileName = "./src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;

        int points = 0;

        for (int i = 0; i < 10; i++) {
            int rand1 = (int) (Math.random() * 30) + 1;
            JSONObject jsonObject = (JSONObject) jsonArray.get(rand1);

            String question = (String) jsonObject.get("question");
            String a = (String) jsonObject.get("option 1");
            String b = (String) jsonObject.get("option 2");
            String c = (String) jsonObject.get("option 3");
            String d = (String) jsonObject.get("option 4");
            Long answer = (long) jsonObject.get("answerKey");

            System.out.println(question);
            System.out.println("1. " + a);
            System.out.println("2. " + b);
            System.out.println("3. " + c);
            System.out.println("4. " + d);

            Scanner input = new Scanner(System.in);
            int answerKey = Integer.parseInt(input.nextLine());

            if (answer == answerKey) {
                System.out.println("Correct Answer");
                points++;
            } else {
                System.out.println("Wrong Answer");
            }
        }

        if (points >= 8) {
            System.out.println("Excellent! You have got " + points + " out of 10");
        } else if (points >= 5 && points < 8) {
            System.out.println("Good. You have got " + points + " out of 10");
        } else if (points >= 2 && points < 5) {
            System.out.println("Very poor! You have got " + points + " out of 10");
        } else if (points >= 0 && points < 2) {
            System.out.println("Very sorry you are failed. You have got " + points + " out of 10");
        }


        System.out.print("Would you like to start again? Press 's' for start or 'q' for quit: ");

    }
}
