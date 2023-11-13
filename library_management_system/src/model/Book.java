package model;

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
                jsonContent.append(scanner.nextLine()).append(System.lineSeparator());
            }

            // Parse the JSON manually and create a list of Book objects
            parseJson(jsonContent.toString(), books);
        }

        return books;
    }

    public static Book findByISBN(List<Book> books, String targetISBN) {
        for (Book book : books) {
            if (book.ISBN.equals(targetISBN)) {
                return book;
            }
        }
        // return null when book not found
        return null;
    }

    public static void deleteBookByISBN(List<Book> books, String targetISBN) {
        Book bookToRemove = findByISBN(books, targetISBN);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book with ISBN " + targetISBN + " has been removed.");
        } else {
            System.out.println("Book with ISBN " + targetISBN + " not found.");
        }
    }

    // using th findByISBN to update book data
    public static void updateBookByISBN(List<Book> books, String targetISBN, Book updatedBook) {
        Book bookToUpdate = findByISBN(books, targetISBN);
        if (bookToUpdate != null) {
            bookToUpdate.title = updatedBook.title;
            bookToUpdate.author = updatedBook.author;
            bookToUpdate.ISBN = updatedBook.ISBN;
            bookToUpdate.setReserved(updatedBook.isReserved);
            bookToUpdate.setCheckedOut(updatedBook.isCheckedOut);
            System.out.println("Book with ISBN " + targetISBN + " has been updated.");
        } else {
            System.out.println("Book with ISBN " + targetISBN + " not found.");
        }
    }

    // AAAA regex is driving me nuts
    private static void parseJson(String jsonContent, List<Book> books) {
        // Remove leading and trailing brackets if present
        jsonContent = jsonContent.trim();
        if (jsonContent.startsWith("[")) {
            jsonContent = jsonContent.substring(1);
        }
        if (jsonContent.endsWith("]")) {
            jsonContent = jsonContent.substring(0, jsonContent.length() - 1);
        }

        String[] bookArray = jsonContent.split("\\},\\s*\\{");

        for (String bookStr : bookArray) {
            String[] keyValuePairs = bookStr.split(",");

            Book book = new Book("", "", "", "");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split(":");
                String key = entry[0].replaceAll("\"", "").trim();
                String value = entry[1].replaceAll("\"", "").trim();
                // using switch so when it detects the attributes in the JSON file it sets
                // title,
                // author, ISBN, reserved, checked out
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
                        // use default to handle other keys if needed
                        break;
                }
            }

            // use add to add the book to the list
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
        if (books.isEmpty()) {
            return "[]";
        }

        StringBuilder json = new StringBuilder("[\n");
        for (Book book : books) {
            json.append("  {\n");
            json.append("    \"title\":\"").append(book.title).append("\",\n");
            json.append("    \"author\":\"").append(book.author).append("\",\n");
            json.append("    \"ISBN\":\"").append(book.ISBN).append("\",\n");
            json.append("    \"reserved\":").append(book.isReserved).append(",\n");
            json.append("    \"checked_out\":").append(book.isCheckedOut).append("\n");
            json.append("  },\n");
        }
        json.delete(json.length() - 2, json.length()); // Remove the trailing comma and newline
        json.append("\n]");

        return json.toString();
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
