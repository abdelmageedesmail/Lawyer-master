package al.ib.lawyer.model;


import com.google.gson.annotations.SerializedName;

public class SecheduledMeetings{

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
			"SecheduledMeetings{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}