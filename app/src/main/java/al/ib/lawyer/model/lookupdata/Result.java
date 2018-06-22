package al.ib.lawyer.model.lookupdata;

import com.google.gson.annotations.SerializedName;


public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("details")
	private String details;

	@SerializedName("LookUP")
	private LookUP lookUP;

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

	public void setLookUP(LookUP lookUP){
		this.lookUP = lookUP;
	}

	public LookUP getLookUP(){
		return lookUP;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"result = '" + result + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			",lookUP = '" + lookUP + '\'' + 
			"}";
		}
}