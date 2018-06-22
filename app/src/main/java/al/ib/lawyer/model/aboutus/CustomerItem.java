package al.ib.lawyer.model.aboutus;

import com.google.gson.annotations.SerializedName;

public class CustomerItem{

	@SerializedName("LocaleKey")
	private String localeKey;

	@SerializedName("LocaleValue")
	private String localeValue;

	@SerializedName("LanguageId")
	private int languageId;

	public void setLocaleKey(String localeKey){
		this.localeKey = localeKey;
	}

	public String getLocaleKey(){
		return localeKey;
	}

	public void setLocaleValue(String localeValue){
		this.localeValue = localeValue;
	}

	public String getLocaleValue(){
		return localeValue;
	}

	public void setLanguageId(int languageId){
		this.languageId = languageId;
	}

	public int getLanguageId(){
		return languageId;
	}

	@Override
 	public String toString(){
		return 
			"CustomerItem{" + 
			"localeKey = '" + localeKey + '\'' + 
			",localeValue = '" + localeValue + '\'' + 
			",languageId = '" + languageId + '\'' + 
			"}";
		}
}