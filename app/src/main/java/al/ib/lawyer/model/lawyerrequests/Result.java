package al.ib.lawyer.model.lawyerrequests;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("OpenRequests")
	private List<OpenRequestsItem> openRequests;

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

	public void setOpenRequests(List<OpenRequestsItem> openRequests){
		this.openRequests = openRequests;
	}

	public List<OpenRequestsItem> getOpenRequests(){
		return openRequests;
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
			",openRequests = '" + openRequests + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}