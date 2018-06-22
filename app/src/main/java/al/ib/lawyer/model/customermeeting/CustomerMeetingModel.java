package al.ib.lawyer.model.customermeeting;


import com.google.gson.annotations.SerializedName;

public class CustomerMeetingModel{

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
			"CustomerMeetingModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}