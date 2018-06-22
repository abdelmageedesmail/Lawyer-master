package al.ib.lawyer.model.requestDetails;

import com.google.gson.annotations.SerializedName;

public class RequestDetailsItem{

	@SerializedName("LawyerID")
	private int lawyerID;

	@SerializedName("TypeName")
	private String typeName;

	@SerializedName("CustomerNameAr")
	private String customerNameAr;

	@SerializedName("UploadDate")
	private String uploadDate;

	@SerializedName("ContentType")
	private String contentType;

	@SerializedName("Comments")
	private Object comments;

	@SerializedName("RType")
	private int rType;

	@SerializedName("Filename")
	private String filename;

	@SerializedName("CustomerNameEn")
	private String customerNameEn;

	@SerializedName("RDescription")
	private String rDescription;

	@SerializedName("Extension")
	private String extension;

	@SerializedName("DownloadGuid")
	private String downloadGuid;

	@SerializedName("RTitle")
	private String rTitle;

	@SerializedName("StatusId")
	private int statusId;

	@SerializedName("Id")
	private int id;

	@SerializedName("PostDate")
	private String postDate;

	@SerializedName("DownloadUrl")
	private String downloadUrl;

	@SerializedName("DownloadBinary")
	private String downloadBinary;

	public void setLawyerID(int lawyerID){
		this.lawyerID = lawyerID;
	}

	public int getLawyerID(){
		return lawyerID;
	}

	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	public String getTypeName(){
		return typeName;
	}

	public void setCustomerNameAr(String customerNameAr){
		this.customerNameAr = customerNameAr;
	}

	public String getCustomerNameAr(){
		return customerNameAr;
	}

	public void setUploadDate(String uploadDate){
		this.uploadDate = uploadDate;
	}

	public String getUploadDate(){
		return uploadDate;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	public void setComments(Object comments){
		this.comments = comments;
	}

	public Object getComments(){
		return comments;
	}

	public void setRType(int rType){
		this.rType = rType;
	}

	public int getRType(){
		return rType;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setCustomerNameEn(String customerNameEn){
		this.customerNameEn = customerNameEn;
	}

	public String getCustomerNameEn(){
		return customerNameEn;
	}

	public void setRDescription(String rDescription){
		this.rDescription = rDescription;
	}

	public String getRDescription(){
		return rDescription;
	}

	public void setExtension(String extension){
		this.extension = extension;
	}

	public String getExtension(){
		return extension;
	}

	public void setDownloadGuid(String downloadGuid){
		this.downloadGuid = downloadGuid;
	}

	public String getDownloadGuid(){
		return downloadGuid;
	}

	public void setRTitle(String rTitle){
		this.rTitle = rTitle;
	}

	public String getRTitle(){
		return rTitle;
	}

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPostDate(String postDate){
		this.postDate = postDate;
	}

	public String getPostDate(){
		return postDate;
	}

	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}

	public String getDownloadUrl(){
		return downloadUrl;
	}

	public void setDownloadBinary(String downloadBinary){
		this.downloadBinary = downloadBinary;
	}

	public String getDownloadBinary(){
		return downloadBinary;
	}

	@Override
 	public String toString(){
		return 
			"RequestDetailsItem{" + 
			"lawyerID = '" + lawyerID + '\'' + 
			",typeName = '" + typeName + '\'' + 
			",customerNameAr = '" + customerNameAr + '\'' + 
			",uploadDate = '" + uploadDate + '\'' + 
			",contentType = '" + contentType + '\'' + 
			",comments = '" + comments + '\'' + 
			",rType = '" + rType + '\'' + 
			",filename = '" + filename + '\'' + 
			",customerNameEn = '" + customerNameEn + '\'' + 
			",rDescription = '" + rDescription + '\'' + 
			",extension = '" + extension + '\'' + 
			",downloadGuid = '" + downloadGuid + '\'' + 
			",rTitle = '" + rTitle + '\'' + 
			",statusId = '" + statusId + '\'' + 
			",id = '" + id + '\'' + 
			",postDate = '" + postDate + '\'' + 
			",downloadUrl = '" + downloadUrl + '\'' + 
			",downloadBinary = '" + downloadBinary + '\'' + 
			"}";
		}
}