package model;

public abstract class Media {
    protected String itemID;
    protected boolean isReserved;
    protected boolean isCheckedOut;

    public Media(String itemID) {
        this.itemID = itemID;
        this.isCheckedOut = false;
        this.isReserved = false;
    }

    public boolean isReserved() {

        return isReserved;
    }

    public boolean isCheckedOut() {
        return this.isCheckedOut;
    }

    public String getItemID() {

        return itemID;
    }

    public void setItemID(String itemID) {

        this.itemID = itemID;
    }

    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.isCheckedOut = checkedOut;
    }

    public abstract void displayInfo();

}
