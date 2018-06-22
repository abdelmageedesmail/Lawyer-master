package al.ib.lawyer.model.packagemodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("result")
	private String result;

	@SerializedName("TotalRecord")
	private int totalRecord;

	@SerializedName("Packages")
	private List<PackagesItem> packages;

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

	public void setPackages(List<PackagesItem> packages){
		this.packages = packages;
	}

	public List<PackagesItem> getPackages(){
		return packages;
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
			",packages = '" + packages + '\'' + 
			",details = '" + details + '\'' + 
			"}";
		}
}