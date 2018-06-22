package al.ib.lawyer.model.customerlogin;

import com.google.gson.annotations.SerializedName;

public class CustomerLoginModel{

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
			"CustomerLoginModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}