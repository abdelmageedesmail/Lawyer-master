package al.ib.lawyer.model.login;


import com.google.gson.annotations.SerializedName;

public class LoginModel{

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
			"LoginModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}