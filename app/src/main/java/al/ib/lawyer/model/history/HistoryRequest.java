package al.ib.lawyer.model.history;


import com.google.gson.annotations.SerializedName;


public class HistoryRequest{

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
			"HistoryRequest{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}