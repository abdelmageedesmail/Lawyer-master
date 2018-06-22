package al.ib.lawyer.model.lookupdata;

import com.google.gson.annotations.SerializedName;

public class LawStatusItem{

	@SerializedName("LawStatus")
	private String lawStatus;

	public void setLawStatus(String lawStatus){
		this.lawStatus = lawStatus;
	}

	public String getLawStatus(){
		return lawStatus;
	}

	@Override
 	public String toString(){
		return 
			"LawStatusItem{" + 
			"lawStatus = '" + lawStatus + '\'' + 
			"}";
		}
}