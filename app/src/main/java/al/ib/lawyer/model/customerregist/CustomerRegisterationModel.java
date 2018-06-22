package al.ib.lawyer.model.customerregist;

import com.google.gson.annotations.SerializedName;

public class CustomerRegisterationModel{

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
			"CustomerRegisterationModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}