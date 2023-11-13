import model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String filePath = "library_management_system\\database\\books.json";

        try {
            // Read books from the JSON file
            List<Book> books = Book.readFromJsonFile(filePath);

            // Display all book names
            /*
             * System.out.println("All Book Titles:");
             * for (Book book : books) {
             * System.out.println(book.title);
             * }
             */

            // Find a book by ISBN
            String targetISBN = "978-0-441-17271-9";
            Book foundBook = Book.findByISBN(books, targetISBN);
            if (foundBook != null) {
                System.out.println("Found Book:");
                foundBook.displayInfo();
            } else {
                System.out.println("Book with ISBN " + targetISBN + " not found.");
            }

            // Add a new book
            Book newBook = new Book("", "New Book", "New Author", "123-456-789");
            books.add(newBook);

            // Update a book by ISBN
            Book updatedBook = new Book("", "Updated Book", "Updated Author", "123-456-789");
            Book.updateBookByISBN(books, "123-456-789", updatedBook);

            // Delete a book by ISBN
            Book.deleteBookByISBN(books, "978-0-345-37077-8");

            // Display all book names after modifications
            System.out.println("\nAll Book Titles After Modifications:");
            for (Book book : books) {
                System.out.println(book.title);
            }

            // using .writeToJsonFile Write modified books back to the JSON file
            Book.writeToJsonFile(books, filePath);
            System.out.println("JSON file updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
