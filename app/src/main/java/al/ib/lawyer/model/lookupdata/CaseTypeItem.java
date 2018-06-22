package al.ib.lawyer.model.lookupdata;

import com.google.gson.annotations.SerializedName;

public class CaseTypeItem{

	@SerializedName("TypeNameAr")
	private String typeNameAr;

	@SerializedName("ID")
	private int iD;

	public void setTypeNameAr(String typeNameAr){
		this.typeNameAr = typeNameAr;
	}

	public String getTypeNameAr(){
		return typeNameAr;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"CaseTypeItem{" + 
			"typeNameAr = '" + typeNameAr + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}