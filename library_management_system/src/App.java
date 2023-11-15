
import java.util.List;

import model.Book;
import model.User;
import view.Index;
import view.media_action.UserMediaAction;
import view.media_list.UserMediaList;

public class App {
    public static void main(String[] args) throws Exception {

        String filePath = "library_management_system/database/Users.json";
        List<User> users = User.readFromJsonFile(filePath);
        System.out.println("User Names:");
        for (User user : users) {
            System.out.println(user.email);
        }
        User newUser = new User("9876543210", "Merlin", "TheCooker", "merlin.cooker@gmail.com");
        users.add(newUser);
        User.writeToJsonFile(users, filePath);
        Index indexPage = new Index();
    }
}
