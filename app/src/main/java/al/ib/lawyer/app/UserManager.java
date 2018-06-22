package al.ib.lawyer.app;

import android.content.Context;
import android.content.SharedPreferences;

import al.ib.lawyer.model.User;

public class UserManager {
    private Context context;

    // SharedPreference
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    private String SHARED_PREF_NAME = "LAWYER_USER_MANAGER";
    private int MODE_PRIVATE = 0;

    // Keys
    private static final String USER_TYPE = "user_type";

    public static final String LAWYER = "LAWYER";
    public static final String USER = "USER";

    private static final String ID = "id";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String PASSWORD = "password";
    private static final String KSALT = "k_salt";
    private static final String EMAIL = "email";
    private static final String IS_LAWYER = "is_lawyer";
    private static final String LAWYER_ID = "lawyer_id";
    private static final String IS_ACTIVE = "is_active";
    private static final String IS_DELETED = "is_deleted";
    private static final String FULL_NAME = "full_name";
    private static final String FULL_NAME_AR = "full_name_ar";
    private static final String FULL_NAME_EN = "full_name";
    private static final String PHONE = "phone";
    private static final String GENDER = "gender";
    private static final String DESCRIPTION = "desc";
    private static final String DESCRIPTION_AR = "desc_ar";
    private static final String MAJOR_CASE_DESCRIPTION_AR = "major_desc_ar";
    private static final String MAJOR_CASE_DESCRIPTION = "major_desc";
    private static final String MEMBER_NUMBER = "member_no";
    private static final String CONSULTATION_FEE = "consultation_fee";
    private static final String OFFICE_TIME_EN = "office_time_en";
    private static final String OFFICE_TIME_AR = "office_time_ar";
    private static final String OFFICE_PHONE_1 = "office_phone_1";
    private static final String OFFICE_PHONE_2 = "office_phone_2";
    private static final String OFFICE_ADDRESS_AR = "office_address_ar";
    private static final String OFFICE_ADDRESS_EN = "office_address_en";
    private static final String PICTURE_ID = "picture_id";
    private static final String PICTURE_URL = "picture_url";
    private static final String CREATED_IN_UTC = "created_in";
    private static final String LAST_LOGIN_DATE_UTC = "last+login_date";
    private static final String LAST_ACTIVITY_DATE_UTC = "last_activity_date";

    private static final String IS_LOGIN = "is_login";
    private static final String HAS_CHOOSE_LANGUAGE = "has_choose_language";
    private static final String LANGUAGE = "language";


    public UserManager(Context context) {
        this.context = context;
        editor = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit();
        pref = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }


    public void setUserType(String userType) {
        editor.putString(USER_TYPE, userType).commit();
    }

    public String getUserType() {
        return pref.getString(USER_TYPE, null);
    }

    public void completeUser(User user){

        editor.putString(DESCRIPTION, user.getDescriptionEn()).apply();
        editor.putString(DESCRIPTION_AR, user.getDescriptionAr()).apply();
        editor.putString(MAJOR_CASE_DESCRIPTION, user.getMajorCaseEn()).apply();
        editor.putString(MAJOR_CASE_DESCRIPTION_AR, user.getMajorCaseAr()).apply();
        editor.putString(MEMBER_NUMBER, user.getMemberNumber()).apply();
        editor.putString(CONSULTATION_FEE, user.getConsultationFee()).apply();
        editor.putString(OFFICE_TIME_EN, user.getOfficeTimeEn()).apply();
        editor.putString(OFFICE_TIME_AR, user.getOfficeTimeAr()).apply();
        editor.putString(OFFICE_PHONE_1, user.getOfficePhone1()).apply();
        editor.putString(OFFICE_PHONE_2, user.getOfficePhone2()).apply();
        editor.putString(OFFICE_ADDRESS_EN, user.getOfficeAddressEn()).apply();
        editor.putString(OFFICE_ADDRESS_AR, user.getOfficeAddressAr()).apply();
        editor.putString(PICTURE_URL, user.getPictureUrl()).apply();

    }
    public void createUser(User user, boolean haveToSaveLogin) {
        editor.putString(ID, user.getId()).apply();
        editor.putString(CUSTOMER_ID, user.getCustomerId()).apply();
        editor.putString(PASSWORD, user.getPassword()).apply();
        editor.putString(KSALT, user.getKslat()).apply();
        editor.putString(EMAIL, user.getEmail()).apply();
        editor.putBoolean(IS_LAWYER, user.isLawyer()).apply();
        editor.putString(LAWYER_ID, user.getLawyerId()).apply();
        editor.putBoolean(IS_ACTIVE, user.isActive()).apply();
        editor.putBoolean(IS_DELETED, user.isDeleted()).apply();
        editor.putString(FULL_NAME, user.getFullName()).apply();
        editor.putString(FULL_NAME_AR, user.getFullNameAr()).apply();
        editor.putString(FULL_NAME_EN, user.getFullNameEn()).apply();
        editor.putString(PHONE, user.getPhone()).apply();
        editor.putString(GENDER, user.getGender()).apply();
        editor.putString(PICTURE_ID, user.getPictureId()).apply();
        editor.putString(CREATED_IN_UTC, user.getCreatedIn()).apply();
        editor.putString(LAST_LOGIN_DATE_UTC, user.getLastLogin()).apply();


        if (haveToSaveLogin)
            editor.putBoolean(IS_LOGIN, true).apply();

    }

    public User getUser() {
        String id, customerId, password, kslat, email, lawyerId, fullName,
                fullNameAr, fullNameEn, phone, gender, pictureId, createdIn, lastLogin, lastActivity,
                descEn, descAr, majorCasEn, majorCaseAR, memberNumber, consultationFee, officeTimeEN, officeTimeAR,
                officePhone1, officePhone2, officeAddressEN, officeAddressAR, picURL;
        boolean isLawyer, isActive, isDeleted;
        id = pref.getString(ID, null);
        customerId = pref.getString(CUSTOMER_ID, null);
        password = pref.getString(PASSWORD, null);
        kslat = pref.getString(KSALT, null);
        email = pref.getString(EMAIL, null);
        isLawyer = pref.getBoolean(IS_LAWYER, false);
        lawyerId = pref.getString(LAWYER_ID, null);
        isActive = pref.getBoolean(IS_ACTIVE, false);
        isDeleted = pref.getBoolean(IS_DELETED, false);
        fullName = pref.getString(FULL_NAME, null);
        fullNameAr = pref.getString(FULL_NAME_AR, null);
        fullNameEn = pref.getString(FULL_NAME_EN, null);
        phone = pref.getString(PHONE, null);
        gender = pref.getString(GENDER, null);
        pictureId = pref.getString(PICTURE_ID, null);
        createdIn = pref.getString(CREATED_IN_UTC, null);
        lastLogin = pref.getString(LAST_LOGIN_DATE_UTC, null);
        lastActivity = pref.getString(LAST_ACTIVITY_DATE_UTC, null);
        descEn = pref.getString(DESCRIPTION, null);
        descAr = pref.getString(DESCRIPTION_AR, null);
        majorCaseAR = pref.getString(MAJOR_CASE_DESCRIPTION_AR, null);
        majorCasEn = pref.getString(MAJOR_CASE_DESCRIPTION, null);
        memberNumber = pref.getString(MEMBER_NUMBER, null);
        consultationFee = pref.getString(CONSULTATION_FEE, null);
        officeTimeEN = pref.getString(OFFICE_TIME_EN, null);
        officeTimeAR = pref.getString(OFFICE_TIME_AR, null);
        officePhone1 = pref.getString(OFFICE_PHONE_1, null);
        officePhone2 = pref.getString(OFFICE_PHONE_2, null);
        officeAddressAR = pref.getString(OFFICE_ADDRESS_AR, null);
        officeAddressEN = pref.getString(OFFICE_ADDRESS_EN, null);
        picURL = pref.getString(PICTURE_URL, null);

        return new User(id, customerId, password, kslat, email, lawyerId, fullName, fullNameAr, fullNameEn, phone,
                gender, pictureId, createdIn, lastLogin, lastActivity, descEn, descAr, majorCasEn, majorCaseAR, memberNumber,
                consultationFee, officeTimeEN, officeTimeAR, officePhone1, officePhone2, officeAddressAR, officeAddressEN,
                picURL, isLawyer, isActive, isDeleted);
    }

    public void setLanguage(String language){
        editor.putString(LANGUAGE, language).apply();
    }

    public String getLanguage(){
        return pref.getString(LANGUAGE, null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setSelectedLanguage(boolean val){
        editor.putBoolean(HAS_CHOOSE_LANGUAGE, val).apply();
    }

    public boolean hasSelectedLanguage(){
        return pref.getBoolean(HAS_CHOOSE_LANGUAGE, false);
    }
    public void logOut(){
        pref.edit().clear().apply();
    }
}
