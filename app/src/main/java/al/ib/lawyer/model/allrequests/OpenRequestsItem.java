package al.ib.lawyer.model.allrequests;


import com.google.gson.annotations.SerializedName;


public class OpenRequestsItem{

	@SerializedName("LawyerID")
	private int lawyerID;

	@SerializedName("Isactive")
	private boolean isactive;

	@SerializedName("EntryUserId")
	private int entryUserId;

	@SerializedName("Comments")
	private String comments;

	@SerializedName("PictureId")
	private int pictureId;

	@SerializedName("RType")
	private int rType;

	@SerializedName("RDescription")
	private String rDescription;

	@SerializedName("LDate")
	private String lDate;

	@SerializedName("RTitle")
	private String rTitle;

	@SerializedName("EntryDate")
	private String entryDate;

	@SerializedName("StatusId")
	private String statusId;

	@SerializedName("Id")
	private int id;

	@SerializedName("LUserId")
	private String lUserId;

	@SerializedName("Isdeleted")
	private boolean isdeleted;

	@SerializedName("PostDate")
	private String postDate;

	@SerializedName("IsPrivate")
	private boolean isPrivate;

	public void setLawyerID(int lawyerID){
		this.lawyerID = lawyerID;
	}

	public int getLawyerID(){
		return lawyerID;
	}

	public void setIsactive(boolean isactive){
		this.isactive = isactive;
	}

	public boolean isIsactive(){
		return isactive;
	}

	public void setEntryUserId(int entryUserId){
		this.entryUserId = entryUserId;
	}

	public int getEntryUserId(){
		return entryUserId;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setPictureId(int pictureId){
		this.pictureId = pictureId;
	}

	public int getPictureId(){
		return pictureId;
	}

	public void setRType(int rType){
		this.rType = rType;
	}

	public int getRType(){
		return rType;
	}

	public void setRDescription(String rDescription){
		this.rDescription = rDescription;
	}

	public String getRDescription(){
		return rDescription;
	}

	public void setLDate(String lDate){
		this.lDate = lDate;
	}

	public String getLDate(){
		return lDate;
	}

	public void setRTitle(String rTitle){
		this.rTitle = rTitle;
	}

	public String getRTitle(){
		return rTitle;
	}

	public void setEntryDate(String entryDate){
		this.entryDate = entryDate;
	}

	public String getEntryDate(){
		return entryDate;
	}

	public void setStatusId(String statusId){
		this.statusId = statusId;
	}

	public String getStatusId(){
		return statusId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setPostDate(String postDate){
		this.postDate = postDate;
	}

	public String getPostDate(){
		return postDate;
	}

	public void setIsPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
	}

	public boolean isIsPrivate(){
		return isPrivate;
	}

	@Override
 	public String toString(){
		return 
			"OpenRequestsItem{" + 
			"lawyerID = '" + lawyerID + '\'' + 
			",isactive = '" + isactive + '\'' + 
			",entryUserId = '" + entryUserId + '\'' + 
			",comments = '" + comments + '\'' + 
			",pictureId = '" + pictureId + '\'' + 
			",rType = '" + rType + '\'' + 
			",rDescription = '" + rDescription + '\'' + 
			",lDate = '" + lDate + '\'' + 
			",rTitle = '" + rTitle + '\'' + 
			",entryDate = '" + entryDate + '\'' + 
			",statusId = '" + statusId + '\'' + 
			",id = '" + id + '\'' + 
			",lUserId = '" + lUserId + '\'' + 
			",isdeleted = '" + isdeleted + '\'' + 
			",postDate = '" + postDate + '\'' + 
			",isPrivate = '" + isPrivate + '\'' + 
			"}";
		}
}