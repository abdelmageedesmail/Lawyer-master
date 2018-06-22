package al.ib.lawyer.model.contract;

import com.google.gson.annotations.SerializedName;

public class DraftContractItem{

	@SerializedName("DraftContractID")
	private int draftContractID;

	public void setDraftContractID(int draftContractID){
		this.draftContractID = draftContractID;
	}

	public int getDraftContractID(){
		return draftContractID;
	}

	@Override
 	public String toString(){
		return 
			"DraftContractItem{" + 
			"draftContractID = '" + draftContractID + '\'' + 
			"}";
		}
}