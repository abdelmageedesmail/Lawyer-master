package al.ib.lawyer.model.privacymodel;

import com.google.gson.annotations.SerializedName;


public class PrivacyModel{

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
			"PrivacyModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}