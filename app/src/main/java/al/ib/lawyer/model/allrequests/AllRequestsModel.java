package al.ib.lawyer.model.allrequests;

import com.google.gson.annotations.SerializedName;


public class AllRequestsModel{

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
			"AllRequestsModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}