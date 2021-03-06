package al.ib.lawyer.model.packagedetails;

import com.google.gson.annotations.SerializedName;

public class PackageDetailsModel{

	@SerializedName("result")
	private Result result;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
		return result;
	}

	@Override
 	public String toString(){
		return 
			"PackageDetailsModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}