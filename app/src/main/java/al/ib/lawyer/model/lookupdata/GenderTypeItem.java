package al.ib.lawyer.model.lookupdata;

import com.google.gson.annotations.SerializedName;

public class GenderTypeItem{

	@SerializedName("Code")
	private String code;

	@SerializedName("Name")
	private String name;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"GenderTypeItem{" + 
			"code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}