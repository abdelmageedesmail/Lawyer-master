package al.ib.lawyer.model.privaterequest;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("details")
	private String details;

	@SerializedName("PrivateRequests")
	private List<PrivateRequestsItem> privateRequests;

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

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setPrivateRequests(List<PrivateRequestsItem> privateRequests){
		this.privateRequests = privateRequests;
	}

	public List<PrivateRequestsItem> getPrivateRequests(){
		return privateRequests;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"result = '" + result + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			",privateRequests = '" + privateRequests + '\'' + 
			"}";
		}
}