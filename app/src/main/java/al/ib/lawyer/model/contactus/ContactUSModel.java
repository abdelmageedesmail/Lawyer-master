package al.ib.lawyer.model.contactus;

import com.google.gson.annotations.SerializedName;

public class ContactUSModel{

	@SerializedName("result")
	private Result result;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
		return result;
	}

	@Override
 	public String toString(){
		return 
			"ContactUSModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}