package al.ib.lawyer.model.lawyerrequests;


import com.google.gson.annotations.SerializedName;

public class LawyerOpenRequestsModel{

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
			"LawyerOpenRequestsModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}