package al.ib.lawyer.model.cscanclemeeting;

import com.google.gson.annotations.SerializedName;

public class UpComingCancelledMeetingItem {

    @SerializedName("LawyerID")
    private int lawyerID;

    @SerializedName("LawyerNameAr")
    private String lawyerNameAr;

    @SerializedName("Description")
    private String description;

    @SerializedName("IsActive")
    private boolean isActive;

    @SerializedName("meetingdtime")
    private String meetingdtime;

    @SerializedName("CustomerID")
    private int customerID;

    @SerializedName("ActionTakenByNameEn")
    private String actionTakenByNameEn;

    @SerializedName("LDate")
    private String lDate;

    @SerializedName("ActionTakenByNameAR")
    private String actionTakenByNameAR;

    @SerializedName("Mdate")
    private String mdate;

    @SerializedName("StatusAr")
    private String statusAr;

    @SerializedName("StatusID")
    private int statusID;

    @SerializedName("ID")
    private int iD;

    @SerializedName("LUserId")
    private String lUserId;

    @SerializedName("Isdeleted")
    private boolean isdeleted;

    @SerializedName("Comment")
    private String comment;

    @SerializedName("CustomerNameAr")
    private String customerNameAr;

    @SerializedName("EntryUserId")
    private int entryUserId;

    @SerializedName("Mtime")
    private String mtime;

    @SerializedName("CustomerNameEn")
    private String customerNameEn;

    @SerializedName("LastNameAr")
    private String lastNameAr;

    @SerializedName("StatusEn")
    private String statusEn;

    @SerializedName("LastNameEn")
    private String lastNameEn;

    @SerializedName("EntryDate")
    private String entryDate;

    @SerializedName("LawyerNameEn")
    private String lawyerNameEn;

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }

    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerNameAr(String lawyerNameAr) {
        this.lawyerNameAr = lawyerNameAr;
    }

    public String getLawyerNameAr() {
        return lawyerNameAr;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setMeetingdtime(String meetingdtime) {
        this.meetingdtime = meetingdtime;
    }

    public String getMeetingdtime() {
        return meetingdtime;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setActionTakenByNameEn(String actionTakenByNameEn) {
        this.actionTakenByNameEn = actionTakenByNameEn;
    }

    public String getActionTakenByNameEn() {
        return actionTakenByNameEn;
    }

    public void setLDate(String lDate) {
        this.lDate = lDate;
    }

    public String getLDate() {
        return lDate;
    }

    public void setActionTakenByNameAR(String actionTakenByNameAR) {
        this.actionTakenByNameAR = actionTakenByNameAR;
    }

    public String getActionTakenByNameAR() {
        return actionTakenByNameAR;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setStatusAr(String statusAr) {
        this.statusAr = statusAr;
    }

    public String getStatusAr() {
        return statusAr;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public int getID() {
        return iD;
    }

    public void setLUserId(String lUserId) {
        this.lUserId = lUserId;
    }

    public String getLUserId() {
        return lUserId;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCustomerNameAr(String customerNameAr) {
        this.customerNameAr = customerNameAr;
    }

    public String getCustomerNameAr() {
        return customerNameAr;
    }

    public void setEntryUserId(int entryUserId) {
        this.entryUserId = entryUserId;
    }

    public int getEntryUserId() {
        return entryUserId;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setCustomerNameEn(String customerNameEn) {
        this.customerNameEn = customerNameEn;
    }

    public String getCustomerNameEn() {
        return customerNameEn;
    }

    public void setLastNameAr(String lastNameAr) {
        this.lastNameAr = lastNameAr;
    }

    public String getLastNameAr() {
        return lastNameAr;
    }

    public void setStatusEn(String statusEn) {
        this.statusEn = statusEn;
    }

    public String getStatusEn() {
        return statusEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setLawyerNameEn(String lawyerNameEn) {
        this.lawyerNameEn = lawyerNameEn;
    }

    public String getLawyerNameEn() {
        return lawyerNameEn;
    }

    @Override
    public String toString() {
        return
                "UpComingCancelledMeetingItem{" +
                        "lawyerID = '" + lawyerID + '\'' +
                        ",lawyerNameAr = '" + lawyerNameAr + '\'' +
                        ",description = '" + description + '\'' +
                        ",isActive = '" + isActive + '\'' +
                        ",meetingdtime = '" + meetingdtime + '\'' +
                        ",customerID = '" + customerID + '\'' +
                        ",actionTakenByNameEn = '" + actionTakenByNameEn + '\'' +
                        ",lDate = '" + lDate + '\'' +
                        ",actionTakenByNameAR = '" + actionTakenByNameAR + '\'' +
                        ",mdate = '" + mdate + '\'' +
                        ",statusAr = '" + statusAr + '\'' +
                        ",statusID = '" + statusID + '\'' +
                        ",iD = '" + iD + '\'' +
                        ",lUserId = '" + lUserId + '\'' +
                        ",isdeleted = '" + isdeleted + '\'' +
                        ",comment = '" + comment + '\'' +
                        ",customerNameAr = '" + customerNameAr + '\'' +
                        ",entryUserId = '" + entryUserId + '\'' +
                        ",mtime = '" + mtime + '\'' +
                        ",customerNameEn = '" + customerNameEn + '\'' +
                        ",lastNameAr = '" + lastNameAr + '\'' +
                        ",statusEn = '" + statusEn + '\'' +
                        ",lastNameEn = '" + lastNameEn + '\'' +
                        ",entryDate = '" + entryDate + '\'' +
                        ",lawyerNameEn = '" + lawyerNameEn + '\'' +
                        "}";
    }
}