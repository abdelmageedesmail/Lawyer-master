package al.ib.lawyer.model.lookupdata;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class LookUP{

	@SerializedName("CaseType")
	private List<CaseTypeItem> caseType;

	@SerializedName("GenderType")
	private List<GenderTypeItem> genderType;

	@SerializedName("LawStatus")
	private List<LawStatusItem> lawStatus;

	public void setCaseType(List<CaseTypeItem> caseType){
		this.caseType = caseType;
	}

	public List<CaseTypeItem> getCaseType(){
		return caseType;
	}

	public void setGenderType(List<GenderTypeItem> genderType){
		this.genderType = genderType;
	}

	public List<GenderTypeItem> getGenderType(){
		return genderType;
	}

	public void setLawStatus(List<LawStatusItem> lawStatus){
		this.lawStatus = lawStatus;
	}

	public List<LawStatusItem> getLawStatus(){
		return lawStatus;
	}

	@Override
 	public String toString(){
		return 
			"LookUP{" + 
			"caseType = '" + caseType + '\'' + 
			",genderType = '" + genderType + '\'' + 
			",lawStatus = '" + lawStatus + '\'' + 
			"}";
		}
}