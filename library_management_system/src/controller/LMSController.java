package controller;

import model.*;

import javax.swing.text.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LMSController {

    public static LMSController lms = new LMSController(true);

    private List<Media> catalog;
    private List<User> users; // Simply two user arrays for single user array

    private GeneralUser currentUser;

    private Boolean isDev = false;

    public LMSController(Boolean isDev) {

        this.catalog = new ArrayList<>();
        this.users = new ArrayList<>();
        this.isDev = isDev; // enables Development features for easy debugging

        // admin account for testing
        if (this.isDev) {
            this.users.add(new Librarian("admin", "admin", "admin", "admin@admin.com", "admin", null));
            this.users.add(new GeneralUser("test", "test", "test", "test@test.com", "test", null));

        }
    }

    // Implement simple TF-IDF search algo

    /*
     * searchCatalog will search our database for items specific to type. For
     * example,
     * if item is a book, we perform search on it's class members(Title, author,
     * ISBN).
     * If item is just media, perform simple compare with itemID.
     */
    public List<Media> searchCatalog(String term) {
        // Will search catalog for item
        List<Media> tmp = new ArrayList<>();
        for (Media curItem : this.catalog) {

            if (curItem instanceof Book) {
                // Perform Book Specific search

            } else {
                // Compare with media
                if (curItem.getItemID().equals(term))
                    tmp.add(curItem);
            }

        }

        return tmp;
    }

    /* Authentication: login, register, forgot(todo) */
    public boolean register(String userID, String firstName, String lastName, String email, String passwd,
            Boolean isAdmin) {
        if (this.doesUserExist(userID)) {
            this.printDevMsg("Register fail, User Exists!");
            return false;
        } else if (passwd == null) {
            this.printDevMsg("Passwd is not good. Make a better one.");
            return false;
        }

        if (isAdmin) {
            this.users.add(new Librarian(userID, firstName, lastName, email, passwd, null));
        } else {
            // this.users.add(new GeneralUser(userID, firstName, lastName, email, passwd,
            // null));
            try {
                users = User.readFromJsonFile("library_management_system/database/Users.json");
                users.add(new GeneralUser(userID, firstName, lastName, email, passwd, null));
                User.writeToJsonFile(users, "library_management_system/database/Users.json");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (this.isDev) {
            if (isAdmin) {
                LMSController.lms.printDevMsg("New admin created!");
            } else {
                LMSController.lms.printDevMsg("New user created!");

            }
        }

        return true;
    }

    public boolean doesUserExist(String userID) {
        try {
            users = User.readFromJsonFile("library_management_system/database/Users.json");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (User u : users) {
            // User exists
            if (u.userID.equalsIgnoreCase(userID)) {
                this.printDevMsg("User exists!");
                return true;
            }
        }
        return false;
    }

    public User login(String userId, String password) {
        if (!this.doesUserExist(userId)) {
            this.printDevMsg("User doesn't exist");
            return null;
        }

        User tmp = this.getUserById(userId);

        if (password.equalsIgnoreCase(tmp.getPassword())) {
            System.out.println("YEAAA!");
            this.currentUser = new GeneralUser(tmp.getUserID(), tmp.getFirstName(), tmp.getLastName(), tmp.getEmail(),
                    tmp.getPassword(), null);
            return tmp;
        }
        return null;
    }

    public void forgotPass() {
        // TODO
        System.out.println("Forgot pass succ");
    }

    /* End Authentication */

    /* Helper Functions */
    public User getUserById(String userID) {
        try {
            users = User.readFromJsonFile("library_management_system/database/Users.json");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (User u : users) {
            if (u.userID.equalsIgnoreCase(userID))
                return u;
        }

        return null;
    }

    public void printDevMsg(String msg) {
        if (this.isDev)
            System.out.println(msg);
    }

    /* End Helper */

    /* setters and getters */
    public List<Media> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Media> catalog) {
        this.catalog = catalog;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Media getMediaById(String ID) {
        for (Media m : this.catalog) {
            if (m.getItemID().equalsIgnoreCase(ID))
                return m;
        }
        return null;
    }

    public int save(){
        ArrayList<Book> tmpL = new ArrayList<>();
        for (Media item: LMSController.lms.getCatalog()) {
            if(item instanceof Book){
                tmpL.add((Book)item);
            }
        }
        try{
            int size = tmpL.size();
            Book.writeToJsonFile(tmpL, "library_management_system/database/books.json");
            return size;
        }catch (IOException exc){
            System.out.println("Failed to write json to file.");
            exc.printStackTrace();
        }
        return 0;
    }

    /* end setters and getters */

}
