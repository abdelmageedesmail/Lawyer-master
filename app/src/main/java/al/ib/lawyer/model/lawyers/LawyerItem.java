package al.ib.lawyer.model.lawyers;

import com.google.gson.annotations.SerializedName;

public class LawyerItem{

	@SerializedName("LawyerID")
	private int lawyerID;

	@SerializedName("LastActivityDateUtc")
	private String lastActivityDateUtc;

	@SerializedName("Email")
	private String email;

	@SerializedName("Description")
	private String description;

	@SerializedName("binaryImage")
	private String binaryImage;

	@SerializedName("MajorcaseDescription")
	private String majorcaseDescription;

	@SerializedName("PictureId")
	private String pictureId;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("ConsultationFee")
	private String consultationFee;

	@SerializedName("DescriptionAR")
	private String descriptionAR;

	@SerializedName("IsLawyer")
	private boolean isLawyer;

	@SerializedName("CreatedOnUtc")
	private String createdOnUtc;

	@SerializedName("Phone1")
	private String phone1;

	@SerializedName("MIMeType")
	private String mIMeType;

	@SerializedName("Phone")
	private String phone;

	@SerializedName("MajorcaseDescriptionAr")
	private String majorcaseDescriptionAr;

	@SerializedName("ksalt")
	private String ksalt;

	@SerializedName("Deleted")
	private boolean deleted;

	@SerializedName("Password")
	private String password;

	@SerializedName("OfficePhone1")
	private String officePhone1;

	@SerializedName("OfficePhone2")
	private String officePhone2;

	@SerializedName("OfficeTimingEn")
	private String officeTimingEn;

	@SerializedName("OfficeAddressEn")
	private String officeAddressEn;

	@SerializedName("pictureURL")
	private String pictureURL;

	@SerializedName("OfficeAddressAr")
	private String officeAddressAr;

	@SerializedName("Active")
	private boolean active;

	@SerializedName("FullNameAr")
	private String fullNameAr;

	@SerializedName("LastLoginDateUtc")
	private String lastLoginDateUtc;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("OfficeTimingAr")
	private String officeTimingAr;

	@SerializedName("FullNameEn")
	private String fullNameEn;

	@SerializedName("MemberNo")
	private String memberNo;

	public void setLawyerID(int lawyerID){
		this.lawyerID = lawyerID;
	}

	public int getLawyerID(){
		return lawyerID;
	}

	public void setLastActivityDateUtc(String lastActivityDateUtc){
		this.lastActivityDateUtc = lastActivityDateUtc;
	}

	public String getLastActivityDateUtc(){
		return lastActivityDateUtc;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setBinaryImage(String binaryImage){
		this.binaryImage = binaryImage;
	}

	public String getBinaryImage(){
		return binaryImage;
	}

	public void setMajorcaseDescription(String majorcaseDescription){
		this.majorcaseDescription = majorcaseDescription;
	}

	public String getMajorcaseDescription(){
		return majorcaseDescription;
	}

	public void setPictureId(String pictureId){
		this.pictureId = pictureId;
	}

	public String getPictureId(){
		return pictureId;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setConsultationFee(String consultationFee){
		this.consultationFee = consultationFee;
	}

	public String getConsultationFee(){
		return consultationFee;
	}

	public void setDescriptionAR(String descriptionAR){
		this.descriptionAR = descriptionAR;
	}

	public String getDescriptionAR(){
		return descriptionAR;
	}

	public void setIsLawyer(boolean isLawyer){
		this.isLawyer = isLawyer;
	}

	public boolean isIsLawyer(){
		return isLawyer;
	}

	public void setCreatedOnUtc(String createdOnUtc){
		this.createdOnUtc = createdOnUtc;
	}

	public String getCreatedOnUtc(){
		return createdOnUtc;
	}

	public void setPhone1(String phone1){
		this.phone1 = phone1;
	}

	public String getPhone1(){
		return phone1;
	}

	public void setMIMeType(String mIMeType){
		this.mIMeType = mIMeType;
	}

	public String getMIMeType(){
		return mIMeType;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setMajorcaseDescriptionAr(String majorcaseDescriptionAr){
		this.majorcaseDescriptionAr = majorcaseDescriptionAr;
	}

	public String getMajorcaseDescriptionAr(){
		return majorcaseDescriptionAr;
	}

	public void setKsalt(String ksalt){
		this.ksalt = ksalt;
	}

	public String getKsalt(){
		return ksalt;
	}

	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}

	public boolean isDeleted(){
		return deleted;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setOfficePhone1(String officePhone1){
		this.officePhone1 = officePhone1;
	}

	public String getOfficePhone1(){
		return officePhone1;
	}

	public void setOfficePhone2(String officePhone2){
		this.officePhone2 = officePhone2;
	}

	public String getOfficePhone2(){
		return officePhone2;
	}

	public void setOfficeTimingEn(String officeTimingEn){
		this.officeTimingEn = officeTimingEn;
	}

	public String getOfficeTimingEn(){
		return officeTimingEn;
	}

	public void setOfficeAddressEn(String officeAddressEn){
		this.officeAddressEn = officeAddressEn;
	}

	public String getOfficeAddressEn(){
		return officeAddressEn;
	}

	public void setPictureURL(String pictureURL){
		this.pictureURL = pictureURL;
	}

	public String getPictureURL(){
		return pictureURL;
	}

	public void setOfficeAddressAr(String officeAddressAr){
		this.officeAddressAr = officeAddressAr;
	}

	public String getOfficeAddressAr(){
		return officeAddressAr;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public void setFullNameAr(String fullNameAr){
		this.fullNameAr = fullNameAr;
	}

	public String getFullNameAr(){
		return fullNameAr;
	}

	public void setLastLoginDateUtc(String lastLoginDateUtc){
		this.lastLoginDateUtc = lastLoginDateUtc;
	}

	public String getLastLoginDateUtc(){
		return lastLoginDateUtc;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setOfficeTimingAr(String officeTimingAr){
		this.officeTimingAr = officeTimingAr;
	}

	public String getOfficeTimingAr(){
		return officeTimingAr;
	}

	public void setFullNameEn(String fullNameEn){
		this.fullNameEn = fullNameEn;
	}

	public String getFullNameEn(){
		return fullNameEn;
	}

	public void setMemberNo(String memberNo){
		this.memberNo = memberNo;
	}

	public String getMemberNo(){
		return memberNo;
	}

	@Override
 	public String toString(){
		return 
			"LawyerItem{" + 
			"lawyerID = '" + lawyerID + '\'' + 
			",lastActivityDateUtc = '" + lastActivityDateUtc + '\'' + 
			",email = '" + email + '\'' + 
			",description = '" + description + '\'' + 
			",binaryImage = '" + binaryImage + '\'' + 
			",majorcaseDescription = '" + majorcaseDescription + '\'' + 
			",pictureId = '" + pictureId + '\'' + 
			",gender = '" + gender + '\'' + 
			",consultationFee = '" + consultationFee + '\'' + 
			",descriptionAR = '" + descriptionAR + '\'' + 
			",isLawyer = '" + isLawyer + '\'' + 
			",createdOnUtc = '" + createdOnUtc + '\'' + 
			",phone1 = '" + phone1 + '\'' + 
			",mIMeType = '" + mIMeType + '\'' + 
			",phone = '" + phone + '\'' + 
			",majorcaseDescriptionAr = '" + majorcaseDescriptionAr + '\'' + 
			",ksalt = '" + ksalt + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",password = '" + password + '\'' + 
			",officePhone1 = '" + officePhone1 + '\'' + 
			",officePhone2 = '" + officePhone2 + '\'' + 
			",officeTimingEn = '" + officeTimingEn + '\'' + 
			",officeAddressEn = '" + officeAddressEn + '\'' + 
			",pictureURL = '" + pictureURL + '\'' + 
			",officeAddressAr = '" + officeAddressAr + '\'' + 
			",active = '" + active + '\'' + 
			",fullNameAr = '" + fullNameAr + '\'' + 
			",lastLoginDateUtc = '" + lastLoginDateUtc + '\'' + 
			",fullName = '" + fullName + '\'' + 
			",officeTimingAr = '" + officeTimingAr + '\'' + 
			",fullNameEn = '" + fullNameEn + '\'' + 
			",memberNo = '" + memberNo + '\'' + 
			"}";
		}
}