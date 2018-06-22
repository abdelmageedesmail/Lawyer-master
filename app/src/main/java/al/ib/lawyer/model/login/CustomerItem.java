package al.ib.lawyer.model.login;


import com.google.gson.annotations.SerializedName;

public class CustomerItem{

	@SerializedName("LawyerID")
	private String lawyerID;

	@SerializedName("LastActivityDateUtc")
	private String lastActivityDateUtc;

	@SerializedName("Email")
	private String email;

	@SerializedName("PictureId")
	private String pictureId;

	@SerializedName("CustomerId")
	private String customerId;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("IsLawyer")
	private boolean isLawyer;

	@SerializedName("CreatedOnUtc")
	private String createdOnUtc;

	@SerializedName("Active")
	private boolean active;

	@SerializedName("FullNameAr")
	private String fullNameAr;

	@SerializedName("LastLoginDateUtc")
	private String lastLoginDateUtc;

	@SerializedName("Phone")
	private String phone;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("ksalt")
	private String ksalt;

	@SerializedName("ID")
	private String iD;

	@SerializedName("Deleted")
	private boolean deleted;

	@SerializedName("FullNameEn")
	private String fullNameEn;

	@SerializedName("Password")
	private String password;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("LastName")
	private String lastName;

	public boolean isLawyer() {
		return isLawyer;
	}

	public void setLawyer(boolean lawyer) {
		isLawyer = lawyer;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLawyerID(String lawyerID){
		this.lawyerID = lawyerID;
	}

	public String getLawyerID(){
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

	public void setPictureId(String pictureId){
		this.pictureId = pictureId;
	}

	public String getPictureId(){
		return pictureId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setKsalt(String ksalt){
		this.ksalt = ksalt;
	}

	public String getKsalt(){
		return ksalt;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}

	public boolean isDeleted(){
		return deleted;
	}

	public void setFullNameEn(String fullNameEn){
		this.fullNameEn = fullNameEn;
	}

	public String getFullNameEn(){
		return fullNameEn;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		return 
			"CustomerItem{" + 
			"lawyerID = '" + lawyerID + '\'' + 
			",lastActivityDateUtc = '" + lastActivityDateUtc + '\'' + 
			",email = '" + email + '\'' + 
			",pictureId = '" + pictureId + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",gender = '" + gender + '\'' + 
			",isLawyer = '" + isLawyer + '\'' + 
			",createdOnUtc = '" + createdOnUtc + '\'' + 
			",active = '" + active + '\'' + 
			",fullNameAr = '" + fullNameAr + '\'' + 
			",lastLoginDateUtc = '" + lastLoginDateUtc + '\'' + 
			",phone = '" + phone + '\'' + 
			",fullName = '" + fullName + '\'' + 
			",ksalt = '" + ksalt + '\'' + 
			",iD = '" + iD + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",fullNameEn = '" + fullNameEn + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}