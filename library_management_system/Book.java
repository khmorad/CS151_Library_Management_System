package CS151_Library_Management_System.library_management_system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        // Remove whitespace and newline characters
        jsonContent = jsonContent.replaceAll("\\s", "");

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
                String key = entry[0].replaceAll("\"", "");
                String value = entry[1].replaceAll("\"", "");

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
