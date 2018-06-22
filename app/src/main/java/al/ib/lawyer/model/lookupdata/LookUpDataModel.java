package al.ib.lawyer.model.lookupdata;

import com.google.gson.annotations.SerializedName;

public class LookUpDataModel{

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
			"LookUpDataModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}