package al.ib.lawyer.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("UpComingCancelledMeeting")
	private List<UpComingCancelledMeetingItem> upComingCancelledMeeting;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("details")
	private String details;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setUpComingCancelledMeeting(List<UpComingCancelledMeetingItem> upComingCancelledMeeting){
		this.upComingCancelledMeeting = upComingCancelledMeeting;
	}

	public List<UpComingCancelledMeetingItem> getUpComingCancelledMeeting(){
		return upComingCancelledMeeting;
	}

	public void setTotalRecord(int totalRecord){
		this.totalRecord = totalRecord;
	}

	public int getTotalRecord(){
		return totalRecord;
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
			",upComingCancelledMeeting = '" + upComingCancelledMeeting + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}