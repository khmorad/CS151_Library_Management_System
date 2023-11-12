package CS151_Library_Management_System.library_management_system;

import java.io.IOException;
import java.util.List;

public class BookMain {
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\khakh\\OneDrive\\desktop\\projects\\library_management_system\\CS151_Library_Management_System\\library_management_system\\database\\books.json";
            List<Book> books = Book.readFromJsonFile(filePath);

            // Display the information of the first book in the original JSON file
            if (!books.isEmpty()) {
                books.get(0).displayInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
