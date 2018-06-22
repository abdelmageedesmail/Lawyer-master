package al.ib.lawyer.view.register;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyer.LawyerModel;
import al.ib.lawyer.model.lawyerregister.LawyerRegisterModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LawyerRegistration extends AppCompatActivity {


    private EditText nameEn, nameAr, descEn, descAr, majorEn, majorAr,
            availableTime, memberNumber, addressAr, addressEn, mobile, telephone, fee, email, pass, rePass,
            officePhone1, officePhone2;
    private TextView register;
    private CheckBox privacyCheckBox;
    private ProgressBar progressBar;
    private FrameLayout wholeView;
    private ImageView pic;
    private UserManager userManager;
    private User user;
    boolean isToEdit = false;

    private static final String TAG = "LawyerRegistration";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_registration);

        nameAr = findViewById(R.id.nameArabic);
        nameEn = findViewById(R.id.nameEnglish);
        descAr = findViewById(R.id.descArabic);
        descEn = findViewById(R.id.descEnglish);
        majorAr = findViewById(R.id.majorArabic);
        majorEn = findViewById(R.id.majorEnglish);
        availableTime = findViewById(R.id.time);
        memberNumber = findViewById(R.id.memmberNumber);
        addressAr = findViewById(R.id.addressArabic);
        addressEn = findViewById(R.id.addressEnglish);
        mobile = findViewById(R.id.mobile);
        telephone = findViewById(R.id.telephone);
        officePhone1 = findViewById(R.id.officePhone1);
        officePhone2 = findViewById(R.id.officePhone2);
        fee = findViewById(R.id.feeAmount);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        rePass = findViewById(R.id.rePass);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.progress);
        wholeView = findViewById(R.id.wholeView);
        privacyCheckBox = findViewById(R.id.acceptThePrivacy);
        pic = findViewById(R.id.pic);

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        if (getIntent().hasExtra("type")) {
            isToEdit = true;
            Toast.makeText(this, "You can update now", Toast.LENGTH_SHORT).show();
            userManager = new UserManager(this);
            user = userManager.getUser();
            nameAr.setText(user.getFullNameAr());
            nameEn.setText(user.getOfficeTimeEn());
            descAr.setText(user.getDescriptionAr());
            descEn.setText(user.getDescriptionEn());
            majorEn.setText(user.getMajorCaseEn());
            majorAr.setText(user.getMajorCaseAr());
            availableTime.setText(user.getOfficeTimeEn());
            addressAr.setText(user.getOfficeAddressAr());
            addressEn.setText(user.getOfficeAddressEn());
            mobile.setText(user.getPhone());
            telephone.setText(user.getOfficePhone1());
            fee.setText(user.getConsultationFee());
            email.setText(user.getEmail());
            Glide.with(this).load(user.getPictureUrl()).into(pic);
            pass.setVisibility(View.GONE);
            rePass.setVisibility(View.GONE);
            register.setText("Save");

        }

        // Launch Time Picker Dialog
        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, TimePickerDialog.THEME_HOLO_DARK,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        availableTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        availableTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (isToEdit) {
                    updateLawyerProfile();
                } else
                    register(view);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });
    }

    private void updateLawyerProfile() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CustomerID", user.getCustomerId());
            jsonObject.put("FullNameEn", nameEn.getText().toString());
            jsonObject.put("FullNameAr", nameAr.getText().toString());
            jsonObject.put("Description", descEn.getText().toString());
            jsonObject.put("DescriptionAR", descAr.getText().toString());
            jsonObject.put("MajorcaseDescription", majorEn.getText().toString());
            jsonObject.put("MajorcaseDescriptionAr", majorAr.getText().toString());
            jsonObject.put("ConsultationFee", fee.getText().toString());
            jsonObject.put("OfficeTimingAr", availableTime.getText().toString());
            jsonObject.put("OfficeTimingEn", availableTime.getText().toString());
            jsonObject.put("OfficePhone1", officePhone1.getText().toString());
            jsonObject.put("OfficePhone2", officePhone2.getText().toString());
            jsonObject.put("OfficeAddressAr", addressAr.getText().toString());
            jsonObject.put("OfficeAddressEn", addressEn.getText().toString());
            jsonObject.put("Phone", mobile.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType type = MediaType.parse("application/raw");
        final RequestBody body = RequestBody.create(type, jsonObject.toString());
        ApiClient.getClient().create(ApiInterface.class).updateLawyerProfile("UpdateCustomerLawyerInfo", user.getEmail(), user.getPassword(), body)
                .enqueue(new RetrofitCallBack<>(this, new Callback<SimpleModelResponse>() {
                    @Override
                    public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {

                        Toast.makeText(LawyerRegistration.this, response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {
                            startActivity(new Intent(LawyerRegistration.this, MainActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SimpleModelResponse> call, Throwable t) {
                        Toast.makeText(LawyerRegistration.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, progressBar, null));
    }

    private void register(final View view) {
        if (isValidInputs()) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nameEN", nameEn.getText().toString());
                jsonObject.put("NameAR", nameAr.getText().toString());
                jsonObject.put("EMail", email.getText().toString());
                jsonObject.put("Mobile", mobile.getText().toString());
                jsonObject.put("DescriptionEn", descEn.getText().toString());
                jsonObject.put("DescriptionAr", descAr.getText().toString());
                jsonObject.put("MajorCaseEn", majorEn.getText().toString());
                jsonObject.put("MajorCaseAr", majorAr.getText().toString());
                jsonObject.put("MemberNumber", Integer.valueOf(memberNumber.getText().toString()));
                jsonObject.put("Feeamount", Double.valueOf(fee.getText().toString()));
                jsonObject.put("Gender", "M");
                jsonObject.put("AvailableTimeAr", availableTime.getText().toString());
                jsonObject.put("AvailableTimeEn", availableTime.getText().toString());
                jsonObject.put("OfficeAddressAr", addressAr.getText().toString());
                jsonObject.put("OfficeAddressEn", addressEn.getText().toString());
                jsonObject.put("OfficePhone", mobile.getText().toString());
                jsonObject.put("OfficePhone1", officePhone1.getText().toString());
                jsonObject.put("OfficePhone2", officePhone2.getText().toString());
                jsonObject.put("UPassword", pass.getText().toString());


                final MediaType mediaType = MediaType.parse("application/raw");
                RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

                ApiClient.getClient().create(ApiInterface.class).registerLawyer("LawyerRegister", body)
                        .enqueue(new RetrofitCallBack<>(getApplicationContext(), new Callback<LawyerRegisterModel>() {
                            @Override
                            public void onResponse(Call<LawyerRegisterModel> call, Response<LawyerRegisterModel> response) {

                                Log.d(TAG, "onResponse: Register " + response.isSuccessful() + ", " + response.body().getResult());
                                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                                    JSONObject object = new JSONObject();
                                    try {
                                        object.put("UserName", email.getText().toString());
                                        object.put("Password", pass.getText().toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    RequestBody body = RequestBody.create(mediaType, String.valueOf(object));
                                    ApiClient.getClient().create(ApiInterface.class).login("Login", email.getText().toString(),
                                            pass.getText().toString(), body).enqueue(new RetrofitCallBack<>(getApplicationContext(),
                                            new Callback<al.ib.lawyer.model.login.LoginModel>() {
                                                @Override
                                                public void onResponse(Call<al.ib.lawyer.model.login.LoginModel> call, Response<al.ib.lawyer.model.login.LoginModel> response) {

                                                    Log.d(TAG, "onResponse Login: " + response.isSuccessful() + ", " + response.body().getResult());
                                                    if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                                                        final UserManager userManager = new UserManager(getApplicationContext());
                                                        userManager.createUser(new User(
                                                                response.body().getResult().getCustomer().get(0).getID(),
                                                                response.body().getResult().getCustomer().get(0).getCustomerId(),
                                                                response.body().getResult().getCustomer().get(0).getPassword(),
                                                                response.body().getResult().getCustomer().get(0).getKsalt(),
                                                                response.body().getResult().getCustomer().get(0).getEmail(),
                                                                response.body().getResult().getCustomer().get(0).getLawyerID(),
                                                                response.body().getResult().getCustomer().get(0).getFullName(),
                                                                response.body().getResult().getCustomer().get(0).getFullNameAr(),
                                                                response.body().getResult().getCustomer().get(0).getFullNameEn(),
                                                                response.body().getResult().getCustomer().get(0).getPhone(),
                                                                response.body().getResult().getCustomer().get(0).getGender(),
                                                                response.body().getResult().getCustomer().get(0).getPictureId(),
                                                                response.body().getResult().getCustomer().get(0).getCreatedOnUtc(),
                                                                response.body().getResult().getCustomer().get(0).getLastLoginDateUtc(),
                                                                response.body().getResult().getCustomer().get(0).getLastActivityDateUtc(),
                                                                response.body().getResult().getCustomer().get(0).isIsLawyer(),
                                                                response.body().getResult().getCustomer().get(0).isActive(),
                                                                response.body().getResult().getCustomer().get(0).isDeleted()), false);
                                                        userManager.setUserType(UserManager.LAWYER);

                                                        Log.d(TAG, "onResponse: " + response.body().getResult().getCustomer().get(0).getLawyerID());
                                                        JSONObject object = new JSONObject();
                                                        try {
                                                            object.put("LawyerID", response.body().getResult().getCustomer().get(0).getLawyerID());
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        RequestBody body = RequestBody.create(mediaType, String.valueOf(object));

                                                        ApiClient.getClient().create(ApiInterface.class).getLawyerByID("GetLawyerByID",
                                                                email.getText().toString(), response.body().getResult().getCustomer().get(0).getPassword(),
                                                                body).enqueue(new RetrofitCallBack<LawyerModel>(LawyerRegistration.this,
                                                                new Callback<LawyerModel>() {
                                                                    @Override
                                                                    public void onResponse(Call<LawyerModel> call, Response<LawyerModel> response) {

                                                                        Log.d(TAG, "onResponse: LawyerByID " + response.body().getResult());

                                                                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                                                                            userManager.completeUser(new User(
                                                                                    response.body().getResult().getLawyer().get(0).getDescription(),
                                                                                    response.body().getResult().getLawyer().get(0).getDescriptionAR(),
                                                                                    response.body().getResult().getLawyer().get(0).getMajorcaseDescription(),
                                                                                    response.body().getResult().getLawyer().get(0).getMajorcaseDescriptionAr(),
                                                                                    response.body().getResult().getLawyer().get(0).getMemberNo(),
                                                                                    response.body().getResult().getLawyer().get(0).getConsultationFee(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficeTimingEn(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficeTimingAr(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficePhone1(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficePhone2(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficeAddressAr(),
                                                                                    response.body().getResult().getLawyer().get(0).getOfficeAddressEn(),
                                                                                    response.body().getResult().getLawyer().get(0).getPictureURL()));

                                                                            Intent intent = new Intent(LawyerRegistration.this, MainActivity.class);
                                                                            intent.putExtra("register", true);
                                                                            startActivity(intent);
                                                                            finish();
                                                                        } else
                                                                            Toast.makeText(LawyerRegistration.this, response.body().getResult().getDetails()
                                                                                    , Toast.LENGTH_SHORT).show();

                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<LawyerModel> call, Throwable t) {

                                                                    }
                                                                }, progressBar, view));


                                                    } else {
                                                        Toast.makeText(LawyerRegistration.this, response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onFailure(Call<al.ib.lawyer.model.login.LoginModel> call, Throwable t) {

                                                }
                                            }, progressBar, wholeView));
                                } else {
                                    Toast.makeText(LawyerRegistration.this, response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<LawyerRegisterModel> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t.getMessage());
                            }
                        }, progressBar, wholeView));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Toast.makeText(LawyerRegistration.this, "Verify Inputs", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = data.getData();
        String imagepath = getRealPathFromURI(this, selectedImageUri);
        Log.d(TAG, "onActivityResult: " + data.getData().getPath());
        File imageFile = new File(data.getData().getPath());
        Glide.with(this).load(imageFile).into(pic);
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        //yourSelectedImage = BitmapFactory.decodeFile(filePath);
        return filePath;
    }

    private boolean isValidInputs() {
        return nameAr.getText().toString().length() > 0 && nameEn.getText().toString().length() > 0 &&
                descAr.getText().toString().length() > 0 && descEn.getText().toString().length() > 0 &&
                majorAr.getText().toString().length() > 0 && majorEn.getText().toString().length() > 0 &&
                availableTime.getText().toString().length() > 0 && memberNumber.getText().toString().length() > 0 &&
                addressAr.getText().toString().length() > 0 && mobile.getText().toString().length() > 5 &&
                telephone.getText().toString().length() > 0 &&
                fee.getText().toString().length() > 0 && email.getText().toString().length() > 3 &&
                pass.getText().toString().length() > 2 &&
                rePass.getText().toString().equals(pass.getText().toString()) && privacyCheckBox.isChecked();
    }
}
