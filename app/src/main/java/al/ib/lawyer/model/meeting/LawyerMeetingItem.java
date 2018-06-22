package al.ib.lawyer.model.meeting;


import com.google.gson.annotations.SerializedName;


public class LawyerMeetingItem{

	@SerializedName("LawyerID")
	private int lawyerID;

	@SerializedName("Comment")
	private String comment;

	@SerializedName("LawyerNameAr")
	private String lawyerNameAr;

	@SerializedName("CustomerNameAr")
	private String customerNameAr;

	@SerializedName("Description")
	private String description;

	@SerializedName("CustomerFullNameEn")
	private String customerFullNameEn;

	@SerializedName("IsActive")
	private boolean isActive;

	@SerializedName("Mtime")
	private String mtime;

	@SerializedName("CustomerNameEn")
	private String customerNameEn;

	@SerializedName("CustomerID")
	private int customerID;

	@SerializedName("LastNameAr")
	private String lastNameAr;

	@SerializedName("CustomerFullNameAr")
	private String customerFullNameAr;

	@SerializedName("StatusEn")
	private String statusEn;

	@SerializedName("LastNameEn")
	private String lastNameEn;

	@SerializedName("LDate")
	private String lDate;

	@SerializedName("Mdate")
	private String mdate;

	@SerializedName("StatusAr")
	private String statusAr;

	@SerializedName("EntryDate")
	private String entryDate;

	@SerializedName("StatusID")
	private int statusID;

	@SerializedName("LawyerNameEn")
	private String lawyerNameEn;

	@SerializedName("ID")
	private int iD;

	@SerializedName("LUserId")
	private String lUserId;

	@SerializedName("Isdeleted")
	private boolean isdeleted;

	public void setLawyerID(int lawyerID){
		this.lawyerID = lawyerID;
	}

	public int getLawyerID(){
		return lawyerID;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setLawyerNameAr(String lawyerNameAr){
		this.lawyerNameAr = lawyerNameAr;
	}

	public String getLawyerNameAr(){
		return lawyerNameAr;
	}

	public void setCustomerNameAr(String customerNameAr){
		this.customerNameAr = customerNameAr;
	}

	public String getCustomerNameAr(){
		return customerNameAr;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCustomerFullNameEn(String customerFullNameEn){
		this.customerFullNameEn = customerFullNameEn;
	}

	public String getCustomerFullNameEn(){
		return customerFullNameEn;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setMtime(String mtime){
		this.mtime = mtime;
	}

	public String getMtime(){
		return mtime;
	}

	public void setCustomerNameEn(String customerNameEn){
		this.customerNameEn = customerNameEn;
	}

	public String getCustomerNameEn(){
		return customerNameEn;
	}

	public void setCustomerID(int customerID){
		this.customerID = customerID;
	}

	public int getCustomerID(){
		return customerID;
	}

	public void setLastNameAr(String lastNameAr){
		this.lastNameAr = lastNameAr;
	}

	public String getLastNameAr(){
		return lastNameAr;
	}

	public void setCustomerFullNameAr(String customerFullNameAr){
		this.customerFullNameAr = customerFullNameAr;
	}

	public String getCustomerFullNameAr(){
		return customerFullNameAr;
	}

	public void setStatusEn(String statusEn){
		this.statusEn = statusEn;
	}

	public String getStatusEn(){
		return statusEn;
	}

	public void setLastNameEn(String lastNameEn){
		this.lastNameEn = lastNameEn;
	}

	public String getLastNameEn(){
		return lastNameEn;
	}

	public void setLDate(String lDate){
		this.lDate = lDate;
	}

	public String getLDate(){
		return lDate;
	}

	public void setMdate(String mdate){
		this.mdate = mdate;
	}

	public String getMdate(){
		return mdate;
	}

	public void setStatusAr(String statusAr){
		this.statusAr = statusAr;
	}

	public String getStatusAr(){
		return statusAr;
	}

	public void setEntryDate(String entryDate){
		this.entryDate = entryDate;
	}

	public String getEntryDate(){
		return entryDate;
	}

	public void setStatusID(int statusID){
		this.statusID = statusID;
	}

	public int getStatusID(){
		return statusID;
	}

	public void setLawyerNameEn(String lawyerNameEn){
		this.lawyerNameEn = lawyerNameEn;
	}

	public String getLawyerNameEn(){
		return lawyerNameEn;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setLUserId(String lUserId){
		this.lUserId = lUserId;
	}

	public String getLUserId(){
		return lUserId;
	}

	public void setIsdeleted(boolean isdeleted){
		this.isdeleted = isdeleted;
	}

	public boolean isIsdeleted(){
		return isdeleted;
	}

	@Override
 	public String toString(){
		return 
			"LawyerMeetingItem{" + 
			"lawyerID = '" + lawyerID + '\'' + 
			",comment = '" + comment + '\'' + 
			",lawyerNameAr = '" + lawyerNameAr + '\'' + 
			",customerNameAr = '" + customerNameAr + '\'' + 
			",description = '" + description + '\'' + 
			",customerFullNameEn = '" + customerFullNameEn + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",mtime = '" + mtime + '\'' + 
			",customerNameEn = '" + customerNameEn + '\'' + 
			",customerID = '" + customerID + '\'' + 
			",lastNameAr = '" + lastNameAr + '\'' + 
			",customerFullNameAr = '" + customerFullNameAr + '\'' + 
			",statusEn = '" + statusEn + '\'' + 
			",lastNameEn = '" + lastNameEn + '\'' + 
			",lDate = '" + lDate + '\'' + 
			",mdate = '" + mdate + '\'' + 
			",statusAr = '" + statusAr + '\'' + 
			",entryDate = '" + entryDate + '\'' + 
			",statusID = '" + statusID + '\'' + 
			",lawyerNameEn = '" + lawyerNameEn + '\'' + 
			",iD = '" + iD + '\'' + 
			",lUserId = '" + lUserId + '\'' + 
			",isdeleted = '" + isdeleted + '\'' + 
			"}";
		}
}