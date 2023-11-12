package CS151_Library_Management_System.library_management_system;

import java.io.IOException;
import java.util.List;

public class BookMain {
    public static void main(String[] args) {
        try {
            String filePath = "CS151_Library_Management_System\\library_management_system\\database\\books.json";
            List<Book> books = Book.readFromJsonFile(filePath);
            // only displaying the first book
            if (!books.isEmpty()) {
                books.get(0).displayInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
