package al.ib.lawyer.model.knetmodel;

import com.google.gson.annotations.SerializedName;

public class KnetModel{

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
			"KnetModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}