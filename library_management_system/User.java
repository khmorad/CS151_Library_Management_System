package CS151_Library_Management_System.library_management_system;

public class User {
    private String userID;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor
    public User(String userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
