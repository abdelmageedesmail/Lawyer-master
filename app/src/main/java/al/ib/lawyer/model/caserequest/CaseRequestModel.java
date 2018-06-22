package al.ib.lawyer.model.caserequest;

import com.google.gson.annotations.SerializedName;

public class CaseRequestModel{

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
			"CaseRequestModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}