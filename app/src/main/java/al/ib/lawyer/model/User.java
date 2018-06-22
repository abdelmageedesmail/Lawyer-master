package al.ib.lawyer.model;

public class User {

    private String id, customerId, password, kslat, email, lawyerId, fullName,
            fullNameAr, fullNameEn, phone, gender, pictureId, createdIn, lastLogin, lastActivity,
            descriptionEn, descriptionAr, majorCaseEn, majorCaseAr, memberNumber, consultationFee,
            officeTimeEn, officeTimeAr, officePhone1, officePhone2, officeAddressAr, officeAddressEn,
            pictureUrl;
    private boolean isLawyer, isActive, isDeleted;

    public User() {

    }

    public User(String id, String customerId, String password, String kslat, String email,
                String lawyerId, String fullName, String fullNameAr, String fullNameEn, String phone,
                String gender, String pictureId, String createdIn, String lastLogin, String lastActivity,
                String descriptionEn, String descriptionAr, String majorCaseEn, String majorCaseAr, String memberNumber
            , String consultationFee, String officeTimeEn, String officeTimeAr, String officePhone1, String officePhone2,
                String officeAddressAr, String officeAddressEn, String pictureUrl,
                boolean isLawyer, boolean isActive, boolean isDeleted) {
        this.id = id;
        this.customerId = customerId;
        this.password = password;
        this.kslat = kslat;
        this.email = email;
        this.lawyerId = lawyerId;
        this.fullName = fullName;
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.phone = phone;
        this.gender = gender;
        this.pictureId = pictureId;
        this.createdIn = createdIn;
        this.lastLogin = lastLogin;
        this.lastActivity = lastActivity;
        this.descriptionEn = descriptionEn;
        this.descriptionAr = descriptionAr;
        this.majorCaseEn = majorCaseEn;
        this.majorCaseAr = majorCaseAr;
        this.memberNumber = memberNumber;
        this.consultationFee = consultationFee;
        this.officeTimeEn = officeTimeEn;
        this.officeTimeAr = officeTimeAr;
        this.officePhone1 = officePhone1;
        this.officePhone2 = officePhone2;
        this.officeAddressAr = officeAddressAr;
        this.officeAddressEn = officeAddressEn;
        this.pictureUrl = pictureUrl;
        this.isLawyer = isLawyer;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public User(String descriptionEn, String descriptionAr, String majorCaseEn, String majorCaseAr,
                String memberNumber, String consultationFee, String officeTimeEn, String officeTimeAr, String officePhone1,
                String officePhone2, String officeAddressAr, String officeAddressEn, String pictureUrl) {
        this.password = password;
        this.kslat = kslat;
        this.email = email;
        this.lawyerId = lawyerId;
        this.fullName = fullName;
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.phone = phone;
        this.gender = gender;
        this.pictureId = pictureId;
        this.createdIn = createdIn;
        this.lastLogin = lastLogin;
        this.lastActivity = lastActivity;
        this.descriptionEn = descriptionEn;
        this.descriptionAr = descriptionAr;
        this.majorCaseEn = majorCaseEn;
        this.majorCaseAr = majorCaseAr;
        this.memberNumber = memberNumber;
        this.consultationFee = consultationFee;
        this.officeTimeEn = officeTimeEn;
        this.officeTimeAr = officeTimeAr;
        this.officePhone1 = officePhone1;
        this.officePhone2 = officePhone2;
        this.officeAddressAr = officeAddressAr;
        this.officeAddressEn = officeAddressEn;
        this.pictureUrl = pictureUrl;
        this.isLawyer = isLawyer;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public User(String id, String customerId, String password, String kslat, String email,
                String lawyerId, String fullName, String fullNameAr, String fullNameEn, String phone,
                String gender, String pictureId, String createdIn, String lastLogin, String lastActivity,
                boolean isLawyer, boolean isActive, boolean isDeleted) {
        this.id = id;
        this.customerId = customerId;
        this.password = password;
        this.kslat = kslat;
        this.email = email;
        this.lawyerId = lawyerId;
        this.fullName = fullName;
        this.fullNameAr = fullNameAr;
        this.fullNameEn = fullNameEn;
        this.phone = phone;
        this.gender = gender;
        this.pictureId = pictureId;
        this.createdIn = createdIn;
        this.lastLogin = lastLogin;
        this.lastActivity = lastActivity;
        this.isLawyer = isLawyer;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKslat() {
        return kslat;
    }

    public void setKslat(String kslat) {
        this.kslat = kslat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(String lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameAr() {
        return fullNameAr;
    }

    public void setFullNameAr(String fullNameAr) {
        this.fullNameAr = fullNameAr;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }

    public boolean isLawyer() {
        return isLawyer;
    }

    public void setLawyer(boolean lawyer) {
        isLawyer = lawyer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getMajorCaseEn() {
        return majorCaseEn;
    }

    public void setMajorCaseEn(String majorCaseEn) {
        this.majorCaseEn = majorCaseEn;
    }

    public String getMajorCaseAr() {
        return majorCaseAr;
    }

    public void setMajorCaseAr(String majorCaseAr) {
        this.majorCaseAr = majorCaseAr;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getOfficeTimeEn() {
        return officeTimeEn;
    }

    public void setOfficeTimeEn(String officeTimeEn) {
        this.officeTimeEn = officeTimeEn;
    }

    public String getOfficeTimeAr() {
        return officeTimeAr;
    }

    public void setOfficeTimeAr(String officeTimeAr) {
        this.officeTimeAr = officeTimeAr;
    }

    public String getOfficePhone1() {
        return officePhone1;
    }

    public void setOfficePhone1(String officePhone1) {
        this.officePhone1 = officePhone1;
    }

    public String getOfficePhone2() {
        return officePhone2;
    }

    public void setOfficePhone2(String officePhone2) {
        this.officePhone2 = officePhone2;
    }

    public String getOfficeAddressAr() {
        return officeAddressAr;
    }

    public void setOfficeAddressAr(String officeAddressAr) {
        this.officeAddressAr = officeAddressAr;
    }

    public String getOfficeAddressEn() {
        return officeAddressEn;
    }

    public void setOfficeAddressEn(String officeAddressEn) {
        this.officeAddressEn = officeAddressEn;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
