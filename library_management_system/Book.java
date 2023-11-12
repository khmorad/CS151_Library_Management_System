package CS151_Library_Management_System.library_management_system;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Book extends Media {
    public String title;
    public String author;
    public String ISBN;

    public Book(String itemID, String title, String author, String ISBN) {
        super(itemID);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public static List<Book> readFromJsonFile(String filePath) throws IOException {
        List<Book> books = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }

            // Parse the JSON manually and create a list of Book objects
            parseJson(jsonContent.toString(), books);
        }

        return books;
    }

    private static void parseJson(String jsonContent, List<Book> books) {
        // Remove leading and trailing brackets if present
        jsonContent = jsonContent.trim();
        if (jsonContent.startsWith("[")) {
            jsonContent = jsonContent.substring(1);
        }
        if (jsonContent.endsWith("]")) {
            jsonContent = jsonContent.substring(0, jsonContent.length() - 1);
        }

        // Split the JSON content into individual book objects
        String[] bookArray = jsonContent.split("\\},\\{");

        for (String bookStr : bookArray) {
            // Remove curly braces and split key-value pairs
            String[] keyValuePairs = bookStr.replaceAll("[{}]", "").split(",");

            // Create a new Book object
            Book book = new Book("", "", "", "");

            // Parse key-value pairs and set book attributes
            for (String pair : keyValuePairs) {
                String[] entry = pair.split(":");
                String key = entry[0].replaceAll("\"", "").trim();
                String value = entry[1].replaceAll("\"", "").trim();
                // using switch so when it detects the attributs in json file it sets
                // the value
                switch (key) {
                    case "title":
                        book.title = value;
                        break;
                    case "author":
                        book.author = value;
                        break;
                    case "ISBN":
                        book.ISBN = value;
                        break;
                    case "reserved":
                        book.setReserved(Boolean.parseBoolean(value));
                        break;
                    case "checked_out":
                        book.setCheckedOut(Boolean.parseBoolean(value));
                        break;
                    default:
                        // Handle other keys if needed
                        break;
                }
            }

            // Add the book to the list
            books.add(book);
        }
    }

    public static void writeToJsonFile(List<Book> books, String filePath) throws IOException {
        // Convert the list of books to JSON format
        String jsonContent = convertListToJson(books);

        // Write the JSON content back to the file
        Files.write(Path.of(filePath), jsonContent.getBytes());
    }

    private static String convertListToJson(List<Book> books) {
        StringBuilder json = new StringBuilder("[");
        for (Book book : books) {
            json.append("{");
            json.append("\"title\":\"").append(escapeJsonString(book.title)).append("\",");
            json.append("\"author\":\"").append(escapeJsonString(book.author)).append("\",");
            json.append("\"ISBN\":\"").append(escapeJsonString(book.ISBN)).append("\",");
            json.append("\"reserved\":").append(book.isReserved).append(",");
            json.append("\"checked_out\":").append(book.isCheckedOut);
            json.append("},");
        }
        if (!books.isEmpty()) {
            json.deleteCharAt(json.length() - 1); // Remove the trailing comma
        }
        json.append("]");
        return json.toString();
    }

    private static String escapeJsonString(String str) {
        // Replace special characters in the string to ensure proper JSON formatting
        return str.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }

    @Override
    public void displayInfo() {
        System.out.println("Book Information:");
        System.out.println("Item ID: " + itemID);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Reserved: " + isReserved);
        System.out.println("Checked Out: " + isCheckedOut);
    }
}
