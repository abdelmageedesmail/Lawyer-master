package al.ib.lawyer.model.lawyerregister;


import com.google.gson.annotations.SerializedName;

public class LawyerRegisterPost{

	@SerializedName("Feeamount")
	private double feeamount;

	@SerializedName("OfficePhone")
	private String officePhone;

	@SerializedName("DescriptionEn")
	private String descriptionEn;

	@SerializedName("MajorCaseAr")
	private String majorCaseAr;

	@SerializedName("nameEN")
	private String nameEN;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("OfficeAddress")
	private String officeAddress;

	@SerializedName("EMail")
	private String eMail;

	@SerializedName("UPassword")
	private String uPassword;

	@SerializedName("Mobile")
	private String mobile;

	@SerializedName("DescriptionAr")
	private String descriptionAr;

	@SerializedName("MajorCaseEn")
	private String majorCaseEn;

	@SerializedName("AvailableTime")
	private String availableTime;

	@SerializedName("MemberNumber")
	private int memberNumber;

	@SerializedName("NameAR")
	private String nameAR;

	public void setFeeamount(double feeamount){
		this.feeamount = feeamount;
	}

	public double getFeeamount(){
		return feeamount;
	}

	public void setOfficePhone(String officePhone){
		this.officePhone = officePhone;
	}

	public String getOfficePhone(){
		return officePhone;
	}

	public void setDescriptionEn(String descriptionEn){
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionEn(){
		return descriptionEn;
	}

	public void setMajorCaseAr(String majorCaseAr){
		this.majorCaseAr = majorCaseAr;
	}

	public String getMajorCaseAr(){
		return majorCaseAr;
	}

	public void setNameEN(String nameEN){
		this.nameEN = nameEN;
	}

	public String getNameEN(){
		return nameEN;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setOfficeAddress(String officeAddress){
		this.officeAddress = officeAddress;
	}

	public String getOfficeAddress(){
		return officeAddress;
	}

	public void setEMail(String eMail){
		this.eMail = eMail;
	}

	public String getEMail(){
		return eMail;
	}

	public void setUPassword(String uPassword){
		this.uPassword = uPassword;
	}

	public String getUPassword(){
		return uPassword;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setDescriptionAr(String descriptionAr){
		this.descriptionAr = descriptionAr;
	}

	public String getDescriptionAr(){
		return descriptionAr;
	}

	public void setMajorCaseEn(String majorCaseEn){
		this.majorCaseEn = majorCaseEn;
	}

	public String getMajorCaseEn(){
		return majorCaseEn;
	}

	public void setAvailableTime(String availableTime){
		this.availableTime = availableTime;
	}

	public String getAvailableTime(){
		return availableTime;
	}

	public void setMemberNumber(int memberNumber){
		this.memberNumber = memberNumber;
	}

	public int getMemberNumber(){
		return memberNumber;
	}

	public void setNameAR(String nameAR){
		this.nameAR = nameAR;
	}

	public String getNameAR(){
		return nameAR;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"feeamount = '" + feeamount + '\'' + 
			",officePhone = '" + officePhone + '\'' + 
			",descriptionEn = '" + descriptionEn + '\'' + 
			",majorCaseAr = '" + majorCaseAr + '\'' + 
			",nameEN = '" + nameEN + '\'' + 
			",gender = '" + gender + '\'' + 
			",officeAddress = '" + officeAddress + '\'' + 
			",eMail = '" + eMail + '\'' + 
			",uPassword = '" + uPassword + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",descriptionAr = '" + descriptionAr + '\'' + 
			",majorCaseEn = '" + majorCaseEn + '\'' + 
			",availableTime = '" + availableTime + '\'' + 
			",memberNumber = '" + memberNumber + '\'' + 
			",nameAR = '" + nameAR + '\'' + 
			"}";
		}
}