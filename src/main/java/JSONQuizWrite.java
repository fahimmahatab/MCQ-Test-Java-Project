import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JSONQuizWrite {
    public static void main() throws IOException, ParseException, IOException {
        char ch = 's';
        String fileName = "./src/main/resources/quiz.json";
        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject quiz = new JSONObject();

            Scanner input = new Scanner(System.in);
            System.out.println("Input your question");
            quiz.put("question", input.nextLine());

            for (int i = 1; i <= 4; i++) {
                System.out.print("Input option " + i + ":");
                String optionText = input.nextLine();
                quiz.put("option " + i, optionText);
            }
            System.out.println("What is the answer key?");
            int answerKey = Integer.parseInt(input.nextLine());
            quiz.put("answerKey", answerKey);

            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(quiz);
            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
            System.out.println("\nSaved successfully! Do you want to add more questions? (press s for start and q for quit)");
            ch = input.next().charAt(0);
        }
        while (ch != 'q');
    }


}





