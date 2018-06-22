package al.ib.lawyer.view.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.customerregist.CustomerRegisterationModel;
import al.ib.lawyer.model.login.LoginModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRegistration extends AppCompatActivity {

    private EditText firstName, lastName, email, password;
    private TextView register;

    private String gender = "M";
    private RadioGroup genderGroupButtons;
    private CheckBox termsCheckBox;

    private ScrollView wholeView;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        firstName = findViewById(R.id.nameEnglish);
        lastName = findViewById(R.id.nameArabic);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        genderGroupButtons = findViewById(R.id.genderGroup);
        termsCheckBox = findViewById(R.id.acceptThePrivacy);
        wholeView = findViewById(R.id.wholeView);
        progress = findViewById(R.id.progress);

        genderGroupButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioButtonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonID);
                int idx = group.indexOfChild(radioButton);

                RadioButton r = (RadioButton) group.getChildAt(idx);

                gender = r.getText().toString();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("FirstName", firstName.getText().toString());
                    jsonObject.put("LastName", lastName.getText().toString());
                    jsonObject.put("EMail", email.getText().toString());
                    jsonObject.put("Gender", gender);
                    jsonObject.put("UPassword", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final MediaType mediaType = MediaType.parse("application/raw");
                RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
                ApiClient.getClient().create(ApiInterface.class).customerRegistration("CustomerRegistration",
                        body).enqueue(new RetrofitCallBack<>(CustomerRegistration.this,
                        new Callback<CustomerRegisterationModel>() {
                            @Override
                            public void onResponse(Call<CustomerRegisterationModel> call, Response<CustomerRegisterationModel> response) {

                                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                                    JSONObject object = new JSONObject();
                                    RequestBody body = RequestBody.create(mediaType, String.valueOf(object));
                                    ApiClient.getClient().create(ApiInterface.class).login("Login",
                                            email.getText().toString(), password.getText().toString(), body).enqueue(
                                            new RetrofitCallBack<>(CustomerRegistration.this,
                                                    new Callback<LoginModel>() {
                                                        @Override
                                                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

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
                                                                        response.body().getResult().getCustomer().get(0).getFirstName(),
                                                                        response.body().getResult().getCustomer().get(0).getLastName(),
                                                                        response.body().getResult().getCustomer().get(0).getPhone(),
                                                                        response.body().getResult().getCustomer().get(0).getGender(),
                                                                        response.body().getResult().getCustomer().get(0).getPictureId(),
                                                                        response.body().getResult().getCustomer().get(0).getCreatedOnUtc(),
                                                                        response.body().getResult().getCustomer().get(0).getLastLoginDateUtc(),
                                                                        response.body().getResult().getCustomer().get(0).getLastActivityDateUtc(),
                                                                        response.body().getResult().getCustomer().get(0).isIsLawyer(),
                                                                        response.body().getResult().getCustomer().get(0).isActive(),
                                                                        response.body().getResult().getCustomer().get(0).isDeleted()), false);
                                                                userManager.setUserType(UserManager.USER);

                                                            } else
                                                                Toast.makeText(CustomerRegistration.this, response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onFailure(Call<LoginModel> call, Throwable t) {

                                                        }
                                                    }, progress, wholeView));

                                } else
                                    Toast.makeText(CustomerRegistration.this, response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<CustomerRegisterationModel> call, Throwable t) {

                            }
                        }, progress, wholeView));
            }
        });

    }
}
