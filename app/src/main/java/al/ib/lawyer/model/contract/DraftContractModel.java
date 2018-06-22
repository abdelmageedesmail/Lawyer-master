package al.ib.lawyer.model.contract;

import com.google.gson.annotations.SerializedName;

public class DraftContractModel{

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
			"DraftContractModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}