package model;

import controller.LMSController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {

    private static List<String> UUIDs = new ArrayList<>();

    public String userID;
    public String firstName;
    public String lastName;
    public String email;
    private String password;

    public File profilePic;

    // Constructor
    public User(String userID, String firstName, String lastName, String email, String passwd, File profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = passwd;
        this.profilePic = profilePic;

        this.userID = userID;
    }

    public boolean verifyPass(String password){
        return password.equalsIgnoreCase(this.password);
    }


    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public File getProfilePic() {
        return profilePic;
    }

}
