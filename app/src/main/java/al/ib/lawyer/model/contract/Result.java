package al.ib.lawyer.model.contract;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("DraftContract")
	private List<DraftContractItem> draftContract;

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

	public void setDraftContract(List<DraftContractItem> draftContract){
		this.draftContract = draftContract;
	}

	public List<DraftContractItem> getDraftContract(){
		return draftContract;
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
			",draftContract = '" + draftContract + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}