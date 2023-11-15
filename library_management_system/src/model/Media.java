package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public abstract class Media {

    public static List<String> uuids = new ArrayList<>();
    protected String itemID;
    protected boolean isReserved;
    protected boolean isCheckedOut;

    public Media(String itemID) {
        this.itemID = Media.generateUUID(this);
        this.isCheckedOut = false;
        this.isReserved = false;
    }

    public boolean isReserved() {

        return isReserved;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getItemID() {

        return itemID;
    }

    public void setItemID(String itemID) {

        this.itemID = itemID;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public abstract void displayInfo();

    public static String generateUUID(Media u){
        UUID tmpUUID = UUID.randomUUID();
        while(Media.uuids.contains(tmpUUID)){
            tmpUUID = UUID.randomUUID();
        }

        return tmpUUID.toString();
    }

}
