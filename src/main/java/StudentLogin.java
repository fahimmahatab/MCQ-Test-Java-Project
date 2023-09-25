import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StudentLogin {
    public static void student(String username, String password) throws IOException, ParseException {
        JSONArray credentials = readUserCredentials();

        if (credentials == null) {
            System.out.println("Unable to read user credentials.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("[For student user]");
//        System.out.println("Enter your username:");
//        String username = scanner.nextLine();
//
//        System.out.println("Enter password:");
//        String password = scanner.nextLine();

        boolean isAuthenticated = authenticateUser(credentials, username, password);

        if (isAuthenticated) {
            System.out.println("Welcome salman to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");

            String choice;
            do {
                choice = scanner.next();
//                scanner.nextLine();
                if (!choice.equals("s")) {
                    break;
                }
                StartQuiz startQuiz = new StartQuiz();
                startQuiz.startQuiz();
            }
            while (true);
//            scanner.close();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
//        scanner.close();
    }

    private static JSONArray readUserCredentials() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("./src/main/resources/users.json"));
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean authenticateUser(JSONArray credentials, String username, String password) {
        for (Object obj : credentials) {
            JSONObject user = (JSONObject) obj;
            String storedUsername = (String) user.get("username");
            String storedPassword = (String) user.get("password");

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return true;
            }
        }

        return false;
    }
}
