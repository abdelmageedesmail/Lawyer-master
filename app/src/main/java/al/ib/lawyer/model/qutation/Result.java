package al.ib.lawyer.model.qutation;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("details")
	private String details;

	@SerializedName("Quotation")
	private List<QuotationItem> quotation;

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

	public void setQuotation(List<QuotationItem> quotation){
		this.quotation = quotation;
	}

	public List<QuotationItem> getQuotation(){
		return quotation;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"result = '" + result + '\'' + 
			",totalRecord = '" + totalRecord + '\'' + 
			",details = '" + details + '\'' + 
			",quotation = '" + quotation + '\'' + 
			"}";
		}
}