package al.ib.lawyer.model.packagemodel;

import com.google.gson.annotations.SerializedName;

public class PackagesItem{

	@SerializedName("KnetURL")
	private String knetURL;

	@SerializedName("TitleAr")
	private String titleAr;

	@SerializedName("Idate")
	private String idate;

	@SerializedName("Price")
	private int price;

	@SerializedName("TotalPeriod")
	private String totalPeriod;

	@SerializedName("Description_ar")
	private String descriptionAr;

	@SerializedName("ID")
	private int iD;

	@SerializedName("TitleEn")
	private String titleEn;

	@SerializedName("Description_en")
	private String descriptionEn;

	public void setKnetURL(String knetURL){
		this.knetURL = knetURL;
	}

	public String getKnetURL(){
		return knetURL;
	}

	public void setTitleAr(String titleAr){
		this.titleAr = titleAr;
	}

	public String getTitleAr(){
		return titleAr;
	}

	public void setIdate(String idate){
		this.idate = idate;
	}

	public String getIdate(){
		return idate;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setTotalPeriod(String totalPeriod){
		this.totalPeriod = totalPeriod;
	}

	public String getTotalPeriod(){
		return totalPeriod;
	}

	public void setDescriptionAr(String descriptionAr){
		this.descriptionAr = descriptionAr;
	}

	public String getDescriptionAr(){
		return descriptionAr;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setTitleEn(String titleEn){
		this.titleEn = titleEn;
	}

	public String getTitleEn(){
		return titleEn;
	}

	public void setDescriptionEn(String descriptionEn){
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionEn(){
		return descriptionEn;
	}

	@Override
 	public String toString(){
		return 
			"PackagesItem{" + 
			"knetURL = '" + knetURL + '\'' + 
			",titleAr = '" + titleAr + '\'' + 
			",idate = '" + idate + '\'' + 
			",price = '" + price + '\'' + 
			",totalPeriod = '" + totalPeriod + '\'' + 
			",description_ar = '" + descriptionAr + '\'' + 
			",iD = '" + iD + '\'' + 
			",titleEn = '" + titleEn + '\'' + 
			",description_en = '" + descriptionEn + '\'' + 
			"}";
		}
}