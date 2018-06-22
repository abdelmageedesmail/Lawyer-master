package al.ib.lawyer.model.lawyerregister;


import com.google.gson.annotations.SerializedName;

public class LawyerRegisterModel {

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
			"LawyerRegisterModel{" +
			"result = '" + result + '\'' + 
			"}";
		}
}