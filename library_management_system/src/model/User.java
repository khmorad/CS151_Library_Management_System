package model;

import controller.LMSController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {

    private static List<String> UUIDs = new ArrayList<>();

    public String userID;
    public String firstName;
    public String lastName;
    public String email;
    private String password;

    public File profilePic;

    // Constructor
    public User(String userID, String firstName, String lastName, String email, String passwd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = passwd;

        this.userID = userID;
    }

    public boolean verifyPass(String password) {
        return password.equalsIgnoreCase(this.password);
    }

    public static List<User> readFromJsonFile(String filePath) throws IOException {
        List<User> users = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine()).append(System.lineSeparator());
            }

            parseJson(jsonContent.toString(), users);
        }

        return users;
    }

    public static void writeToJsonFile(List<User> users, String filePath) throws IOException {
        String jsonContent = convertListToJson(users);
        Files.write(Path.of(filePath), jsonContent.getBytes());
    }

    private static void parseJson(String jsonContent, List<User> users) {
        jsonContent = jsonContent.trim();
        // remove the spaces of json file using trip then remove the closing
        // and opening brackets
        // {
        // "userID": "431",
        // .
        // .
        // }
        if (jsonContent.startsWith("[")) {
            jsonContent = jsonContent.substring(1);
        }
        if (jsonContent.endsWith("]")) {
            jsonContent = jsonContent.substring(0, jsonContent.length() - 1);
        }
        // \\}, represents closing bracket and comma
        // \\s* represent (space|tab|newline) and { is opening bracket of next json item
        String[] userArray = jsonContent.split("\\},\\s*\\{");

        for (String userStr : userArray) {
            // now removing all the bracket which all its left now is the comma whihch then
            // we use
            // .split function to inset it into a string array
            userStr = userStr.replace("{", "").replace("}", ""); // Remove extra curly braces

            String[] keyValuePairs = userStr.split(",");

            User user = new User("", "", "", "", "");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split(":");
                String key = entry[0].replaceAll("\"", "").trim();
                String value = entry[1].replaceAll("\"", "").trim();

                switch (key) {
                    case "userID":
                        user.userID = value;
                        break;
                    case "first_name":
                        user.firstName = value;
                        break;
                    case "last_name":
                        user.lastName = value;
                        break;
                    case "email":
                        user.email = value;
                        break;
                    case "password":
                        user.password = value;
                        break;
                    default:
                        break;
                }
            }

            users.add(user);
        }
    }

    public static User findUserByID(List<User> users, String targetUserID) {
        for (User user : users) {
            if (user.userID.equals(targetUserID)) {
                return user;
            }
        }
        return null;
    }

    private static String convertListToJson(List<User> users) {
        if (users.isEmpty()) {
            return "[]";
        }

        StringBuilder json = new StringBuilder("[\n");
        for (User user : users) {
            json.append("  {\n");
            json.append("    \"userID\":\"").append(user.userID).append("\",\n");
            json.append("    \"first_name\":\"").append(user.firstName).append("\",\n");
            json.append("    \"last_name\":\"").append(user.lastName).append("\",\n");
            json.append("    \"email\":\"").append(user.email).append("\",\n");
            json.append("    \"password\":\"").append(user.password).append("\"\n");
            json.append("  },\n");
        }
        json.delete(json.length() - 2, json.length());
        json.append("\n]");

        return json.toString();
    }

    public static void removeUserByID(List<User> users, String targetUserID) {
        User userToRemove = findUserByID(users, targetUserID);
        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("User with ID " + targetUserID + " has been removed.");
        } else {
            System.out.println("User with ID " + targetUserID + " not found.");
        }
    }

    public void displayInfo() {
        System.out.println("User Information:");
        System.out.println("id num: " + userID);
        System.out.println("first name: " + firstName);
        System.out.println("last name: " + lastName);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
    }

    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
