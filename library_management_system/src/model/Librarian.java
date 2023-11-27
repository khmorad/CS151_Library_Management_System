package model;

import controller.LMSController;

import java.io.File;

public class Librarian extends User{
    public Librarian(String userID, String firstName, String lastName, String email, String passwd, File profilePic) {
        super(userID, firstName, lastName, email, passwd);
    }

    public boolean updateMedia(String itemIDToUpdate){
        //Perform update on mediaItem
        Media item = LMSController.lms.getMediaById(itemIDToUpdate);

        if(item != null){

            if(item instanceof Book){

            }

        }

        return true;
    }
}
