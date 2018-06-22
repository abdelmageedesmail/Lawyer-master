package al.ib.lawyer.model.knetmodel;

import com.google.gson.annotations.SerializedName;

public class RequestDetailsItem{

	@SerializedName("KnetURL")
	private String knetURL;

	@SerializedName("LawyerID")
	private String lawyerID;

	@SerializedName("Price")
	private String price;

	@SerializedName("PackageID")
	private String packageID;

	public void setKnetURL(String knetURL){
		this.knetURL = knetURL;
	}

	public String getKnetURL(){
		return knetURL;
	}

	public void setLawyerID(String lawyerID){
		this.lawyerID = lawyerID;
	}

	public String getLawyerID(){
		return lawyerID;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setPackageID(String packageID){
		this.packageID = packageID;
	}

	public String getPackageID(){
		return packageID;
	}

	@Override
 	public String toString(){
		return 
			"RequestDetailsItem{" + 
			"knetURL = '" + knetURL + '\'' + 
			",lawyerID = '" + lawyerID + '\'' + 
			",price = '" + price + '\'' + 
			",packageID = '" + packageID + '\'' + 
			"}";
		}
}