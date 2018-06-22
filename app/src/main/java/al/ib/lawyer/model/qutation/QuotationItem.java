package al.ib.lawyer.model.qutation;

import com.google.gson.annotations.SerializedName;

public class QuotationItem{

	@SerializedName("QuotationID")
	private int quotationID;

	public void setQuotationID(int quotationID){
		this.quotationID = quotationID;
	}

	public int getQuotationID(){
		return quotationID;
	}

	@Override
 	public String toString(){
		return 
			"QuotationItem{" + 
			"quotationID = '" + quotationID + '\'' + 
			"}";
		}
}