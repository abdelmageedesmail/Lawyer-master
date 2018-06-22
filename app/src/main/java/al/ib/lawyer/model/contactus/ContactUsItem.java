package al.ib.lawyer.model.contactus;

import com.google.gson.annotations.SerializedName;

public class ContactUsItem{

	@SerializedName("ContactUsID")
	private int contactUsID;

	public void setContactUsID(int contactUsID){
		this.contactUsID = contactUsID;
	}

	public int getContactUsID(){
		return contactUsID;
	}

	@Override
 	public String toString(){
		return 
			"ContactUsItem{" + 
			"contactUsID = '" + contactUsID + '\'' + 
			"}";
		}
}