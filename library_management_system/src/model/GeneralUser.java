package model;

import controller.LMSController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneralUser extends User{

    private List<Media> checkedOut;


    public GeneralUser(String userID, String firstName, String lastName, String email, String passwd, File profilePic) {
        super(userID, firstName, lastName, email, passwd, profilePic);
        this.checkedOut = new ArrayList<>();
    }

    /*User Functions: check out, return, update */
    public boolean checkOut(String itemToCheckoutID){
        //Perform checkout
        Media itemToCheckout = LMSController.lms.getMediaById(itemToCheckoutID);
        if(itemToCheckout == null){
            LMSController.lms.printDevMsg("Item doesn't exist!");
            return false;
        }

        if(this.getCheckedOut().contains(itemToCheckout)){
            LMSController.lms.printDevMsg("Failed to check out!");
            return false;
        }
        this.getCheckedOut().add(itemToCheckout);
        itemToCheckout.setCheckedOut(true);
        return true;
    }

    public boolean returnMedia(Media itemToReturn){
        //Perform return
        if(!this.getCheckedOut().contains(itemToReturn)){
            LMSController.lms.printDevMsg(LMSController.lms.getCurrentUser().userID + " didn't check out Item: " +itemToReturn.getItemID());
        }
        this.getCheckedOut().remove(itemToReturn);
        itemToReturn.setCheckedOut(false);

        return true;
    }

    /*End Library Function*/

    public List<Media> getCheckedOut() {
        return checkedOut;
    }
}
