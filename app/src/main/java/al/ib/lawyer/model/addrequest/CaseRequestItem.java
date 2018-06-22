package al.ib.lawyer.model.addrequest;

import com.google.gson.annotations.SerializedName;

public class CaseRequestItem{

	@SerializedName("CaseRequestID")
	private int caseRequestID;

	public void setCaseRequestID(int caseRequestID){
		this.caseRequestID = caseRequestID;
	}

	public int getCaseRequestID(){
		return caseRequestID;
	}

	@Override
 	public String toString(){
		return 
			"CaseRequestItem{" + 
			"caseRequestID = '" + caseRequestID + '\'' + 
			"}";
		}
}