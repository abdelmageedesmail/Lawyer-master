package al.ib.lawyer.model.customerlogin;

import com.google.gson.annotations.SerializedName;

public class CustomerItem{

	@SerializedName("LawyerID")
	private int lawyerID;

	@SerializedName("LastActivityDateUtc")
	private String lastActivityDateUtc;

	@SerializedName("Email")
	private String email;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("PictureId")
	private Object pictureId;

	@SerializedName("CustomerId")
	private int customerId;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("IsLawyer")
	private boolean isLawyer;

	@SerializedName("CreatedOnUtc")
	private String createdOnUtc;

	@SerializedName("Active")
	private boolean active;

	@SerializedName("LastLoginDateUtc")
	private String lastLoginDateUtc;

	@SerializedName("Phone")
	private Object phone;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("ksalt")
	private String ksalt;

	@SerializedName("ID")
	private int iD;

	@SerializedName("Deleted")
	private boolean deleted;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("Password")
	private String password;

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

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setPictureId(Object pictureId){
		this.pictureId = pictureId;
	}

	public Object getPictureId(){
		return pictureId;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
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

	public void setLastLoginDateUtc(String lastLoginDateUtc){
		this.lastLoginDateUtc = lastLoginDateUtc;
	}

	public String getLastLoginDateUtc(){
		return lastLoginDateUtc;
	}

	public void setPhone(Object phone){
		this.phone = phone;
	}

	public Object getPhone(){
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

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}

	public boolean isDeleted(){
		return deleted;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
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
			",firstName = '" + firstName + '\'' + 
			",pictureId = '" + pictureId + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",gender = '" + gender + '\'' + 
			",isLawyer = '" + isLawyer + '\'' + 
			",createdOnUtc = '" + createdOnUtc + '\'' + 
			",active = '" + active + '\'' + 
			",lastLoginDateUtc = '" + lastLoginDateUtc + '\'' + 
			",phone = '" + phone + '\'' + 
			",fullName = '" + fullName + '\'' + 
			",ksalt = '" + ksalt + '\'' + 
			",iD = '" + iD + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}