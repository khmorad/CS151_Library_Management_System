package CS151_Library_Management_System.library_management_system;

import java.io.File;
public class User {
    public String userID;
    public String firstName;
    public String lastName;
    public String email;
    public File profilePic;

    // Constructor
    public User(String userID, String firstName, String lastName, String email, File profilePic) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
    }
}
