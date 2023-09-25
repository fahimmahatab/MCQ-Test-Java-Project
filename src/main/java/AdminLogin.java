import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AdminLogin {
    public static void admin(String username,String password) throws IOException, ParseException {
        JSONArray credentials = readUserCredentials();

        if (credentials == null) {
            System.out.println("Unable to read user credentials.");
            return;
        }
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("[For admin user]");
//        System.out.println("Enter your username:");
//        String username = scanner.nextLine();
//
//        System.out.println("Enter password:");
//        String password = scanner.nextLine();

        boolean isAuthenticated = authenticateUser(credentials, username, password);

        if (isAuthenticated) {
            System.out.println("Welcome admin! Please create new questions in the question bank.");
            JSONQuizWrite jsonQuizWrite=new JSONQuizWrite();
            jsonQuizWrite.main();
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
