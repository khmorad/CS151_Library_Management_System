package controller;

import model.*;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class LMSController {

    public static LMSController lms = new LMSController(true);

    private List<Media> catalog;
    private List<User> users; // Simply two user arrays for single user array

    private User currentUser;

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
            this.users.add(new GeneralUser(userID, firstName, lastName, email, passwd, null));
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
        for (User u : this.getUsers()) {
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
        if (tmp != null) {
            if (tmp.verifyPass(password)) {
                this.printDevMsg("Login Succ");
                this.currentUser = tmp;
                return tmp;
            }
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
        for (User u : this.getUsers()) {
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

    /* end setters and getters */

}
