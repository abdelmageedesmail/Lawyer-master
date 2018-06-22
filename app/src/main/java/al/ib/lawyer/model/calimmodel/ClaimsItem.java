package al.ib.lawyer.model.calimmodel;

import com.google.gson.annotations.SerializedName;

public class ClaimsItem{

	@SerializedName("ClaimID")
	private int claimID;

	public void setClaimID(int claimID){
		this.claimID = claimID;
	}

	public int getClaimID(){
		return claimID;
	}

	@Override
 	public String toString(){
		return 
			"ClaimsItem{" + 
			"claimID = '" + claimID + '\'' + 
			"}";
		}
}