package al.ib.lawyer.model.lawyer;


import com.google.gson.annotations.SerializedName;

public class LawyerModel{

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
			"LawyerModel{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}