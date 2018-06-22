package al.ib.lawyer.model.history;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("HistoryRequests")
	private List<HistoryRequestsItem> historyRequests;

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

	public void setHistoryRequests(List<HistoryRequestsItem> historyRequests){
		this.historyRequests = historyRequests;
	}

	public List<HistoryRequestsItem> getHistoryRequests(){
		return historyRequests;
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
			",historyRequests = '" + historyRequests + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}