package al.ib.lawyer.model.cscanclemeeting;

import com.google.gson.annotations.SerializedName;


public class CanceledMeetingModel{

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
			"CanceledMeetingModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}