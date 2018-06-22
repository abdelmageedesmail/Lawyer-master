package al.ib.lawyer.model.lawyermeeting;

import com.google.gson.annotations.SerializedName;

public class LawyerMeetingModel{

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
			"LawyerMeetingModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}