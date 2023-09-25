import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws IOException, ParseException {

        Scanner i = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        switch (userRole(username, password)) {
            case "admin":
                admin(username, password);
                break;
            case "student":
                student(username, password);
                break;
            default:
                System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void admin(String username, String password) throws IOException, ParseException {
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.admin(username, password);
    }

    private static void student(String username, String password) throws IOException, ParseException {
        StudentLogin studentLogin = new StudentLogin();
        studentLogin.student(username, password);
    }

    public static String userRole(String name, String password) throws IOException {

        JSONArray jsonArray = new JSONArray();
        try (FileReader fileReader = new FileReader("./src/main/resources/users.json")) {
            if (fileReader.read() < 1) {
                System.out.println("Error occurred. Please try again!");
            } else {
                FileReader fileReader1 = new FileReader("./src/main/resources/users.json");
                JSONParser jsonParser = new JSONParser();
                jsonArray = (JSONArray) jsonParser.parse(fileReader1);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        for (Object obj : jsonArray) {
            JSONObject userObj = (JSONObject) obj;
            String storedName = (String) userObj.get("username");
            String storedPassword = (String) userObj.get("password");

            if (storedName.equals(name) && storedPassword.equals(password)) {
                return (String) userObj.get("role");
            }
        }
        return "";
    }
}


