import java.io.IOException;
import java.util.List;

import model.Book;
import view.Index;

public class App {
    public static void main(String[] args) throws Exception {
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
        Index indexPage = new Index();

    }
}
