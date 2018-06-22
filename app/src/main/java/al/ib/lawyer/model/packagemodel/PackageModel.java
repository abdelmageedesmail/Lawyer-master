package al.ib.lawyer.model.packagemodel;

import com.google.gson.annotations.SerializedName;

public class PackageModel{

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
			"PackageModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}