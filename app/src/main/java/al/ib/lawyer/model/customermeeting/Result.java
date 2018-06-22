package al.ib.lawyer.model.customermeeting;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("CustomerMeeting")
	private List<CustomerMeetingItem> customerMeeting;

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

	public void setCustomerMeeting(List<CustomerMeetingItem> customerMeeting){
		this.customerMeeting = customerMeeting;
	}

	public List<CustomerMeetingItem> getCustomerMeeting(){
		return customerMeeting;
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
			",customerMeeting = '" + customerMeeting + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}