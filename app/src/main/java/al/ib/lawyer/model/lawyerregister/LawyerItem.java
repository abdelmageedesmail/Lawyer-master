package al.ib.lawyer.model.lawyerregister;


import com.google.gson.annotations.SerializedName;

public class LawyerItem {

    @SerializedName("UserID")
    private int userID;

    @SerializedName("lawyerId")
    private int lawyerId;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setLawyerId(int lawyerId) {
        this.lawyerId = lawyerId;
    }

    public int getLawyerId() {
        return lawyerId;
    }

    @Override
    public String toString() {
        return
                "LawyerItem{" +
                        "userID = '" + userID + '\'' +
                        ",lawyerId = '" + lawyerId + '\'' +
                        "}";
    }
}