package al.ib.lawyer.model.lawyermeeting;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("OpenRequests")
	private List<LawyerMeetingItem> lawyerMeeting;

	@SerializedName("details")
	private String details;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTotalRecord(int totalRecord){
		this.totalRecord = totalRecord;
	}

	public int getTotalRecord(){
		return totalRecord;
	}

	public void setLawyerMeeting(List<LawyerMeetingItem> lawyerMeeting){
		this.lawyerMeeting = lawyerMeeting;
	}

	public List<LawyerMeetingItem> getLawyerMeeting(){
		return lawyerMeeting;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"result = '" + result + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",lawyerMeeting = '" + lawyerMeeting + '\'' +
			",details = '" + details + '\'' + 
			"}";
		}
}