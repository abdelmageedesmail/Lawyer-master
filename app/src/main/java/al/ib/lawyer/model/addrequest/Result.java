package al.ib.lawyer.model.addrequest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("details")
	private String details;

	@SerializedName("caseRequest")
	private List<CaseRequestItem> caseRequest;

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

	public void setCaseRequest(List<CaseRequestItem> caseRequest){
		this.caseRequest = caseRequest;
	}

	public List<CaseRequestItem> getCaseRequest(){
		return caseRequest;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"result = '" + result + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			",caseRequest = '" + caseRequest + '\'' + 
			"}";
		}
}