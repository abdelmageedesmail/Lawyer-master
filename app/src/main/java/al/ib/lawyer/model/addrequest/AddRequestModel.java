package al.ib.lawyer.model.addrequest;

import com.google.gson.annotations.SerializedName;

public class AddRequestModel{

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
			"AddRequestModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}