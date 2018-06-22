package al.ib.lawyer.model.aboutus;

import com.google.gson.annotations.SerializedName;


public class AboutUsModel{

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
			"AboutUsModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}