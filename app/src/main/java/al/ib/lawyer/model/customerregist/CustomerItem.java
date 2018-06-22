package al.ib.lawyer.model.customerregist;

import com.google.gson.annotations.SerializedName;

public class CustomerItem{

	@SerializedName("UserID")
	private int userID;

	public void setUserID(int userID){
		this.userID = userID;
	}

	public int getUserID(){
		return userID;
	}

	@Override
 	public String toString(){
		return 
			"CustomerItem{" + 
			"userID = '" + userID + '\'' + 
			"}";
		}
}