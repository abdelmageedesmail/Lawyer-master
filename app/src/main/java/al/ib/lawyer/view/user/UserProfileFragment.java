package al.ib.lawyer.view.user;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lookupdata.LookUpDataModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {


    public static final String TAG = "UserProfileFragment";

    public UserProfileFragment() {
        // Required empty public constructor
    }


    private TextView txtChangePassword;

    private Dialog changePassDialog;
    private UserManager userManager;
    private User user;
    private EditText firstName, lastName, email ;
    private TextView save;
    private ProgressBar progressBar;
    RadioButton registerMale,registerFemale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        txtChangePassword = view.findViewById(R.id.changePassword);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        save = view.findViewById(R.id.save);
        progressBar = view.findViewById(R.id.progress);
        email = view.findViewById(R.id.email);
        registerFemale=view.findViewById(R.id.registerFemale);
        registerMale=view.findViewById(R.id.registerMale);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        firstName.setText(user.getFullName());
        lastName.setText(user.getFullNameEn());
        getUserData();
        email.setText(user.getEmail());
        if (user.getGender().equals("Male")){
            registerMale.setChecked(true);
        }else{
            registerFemale.setChecked(false);
        }

        Log.e("userData",""+user.getFullName()+".."+user.getGender()+"....."+user.getFullNameAr());
        initChangePasswordDialog();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("CustomerID", user.getCustomerId());
                    jsonObject.put("FirstName", firstName.getText().toString());
                    jsonObject.put("LastName", lastName.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());
                ApiClient.getClient().create(ApiInterface.class).updateCustomerProfile("UpdateCustomerLawyerInfo", user.getEmail(), user.getPassword(), body)
                        .enqueue(new RetrofitCallBack<>(getActivity(), new Callback<SimpleModelResponse>() {
                            @Override
                            public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {

                                Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<SimpleModelResponse> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, progressBar, null));
            }
        });
        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassDialog.show();
            }
        });

        return view;
    }

    private void getUserData(){

        final JSONObject object = new JSONObject();
        final MediaType mediaType = MediaType.parse("application/raw");
        try {
            object.put("Email", user.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.initialize(getActivity());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addJSONObjectBody(object)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("MethodName", "GetCustomerByEmail")
                .addHeaders("x-user", user.getEmail())
                .addHeaders("x-pass", user.getPassword())
                .setPriority(Priority.MEDIUM)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    if (result.getString("result").equals("OK")) {
                        JSONObject customer = result.getJSONObject("Customer");
                        JSONArray array=customer.getJSONArray("Table");
                        JSONObject jsonObject1 = array.getJSONObject(0);
                        firstName.setText(jsonObject1.getString("FirstName"));
                        lastName.setText(jsonObject1.getString("LastName"));

//                        if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
//                            txtName.setText(jsonObject.getString("FullNameAr"));
//                            txtDesc.setText(jsonObject.getString("DescriptionAR"));
//                            availableTime.setText(jsonObject.getString("AvailableTimeAR"));
//                            address.setText(jsonObject.getString("OfficeAddressAr"));
//                            major.setText(jsonObject.getString("MajorcaseDescriptionAR"));
//                        } else {
//                            txtName.setText(jsonObject.getString("FullNameEn"));
//                            txtDesc.setText(jsonObject.getString("DescriptionEn"));
//                            availableTime.setText(jsonObject.getString("AvailableTimeEN"));
//                            address.setText(jsonObject.getString("OfficeAddressEn"));
//                            major.setText(jsonObject.getString("MajorcaseDescriptionEN"));
//                        }
//                        fee.setText(jsonObject.getString("ConsultationFee"));
//                        site.setText(jsonObject.getString("Email"));
//                        phone.setText(object.getString("Mobile"));
//                        Glide.with(getActivity()).load(jsonObject.getString("pictureURL")).into(pic);
//                        localeShared.storeKey("mobile",object.getString("Mobile"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
    private void initChangePasswordDialog() {
        changePassDialog = new Dialog(getActivity());
        changePassDialog.setContentView(R.layout.change_password_pop_up);

        final EditText password = changePassDialog.findViewById(R.id.password);
        TextView save = changePassDialog.findViewById(R.id.save);
        final ProgressBar progress = changePassDialog.findViewById(R.id.progress);
        final LinearLayout wholeView = changePassDialog.findViewById(R.id.wholeView);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("UserID", user.getId());
                    jsonObject.put("UPassword", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());
                ApiClient.getClient().create(ApiInterface.class).changePassword("ChangeUserPassword", user.getEmail(), user.getPassword(), body)
                        .enqueue(new RetrofitCallBack<>(getActivity(), new Callback<LookUpDataModel>() {
                            @Override
                            public void onResponse(Call<LookUpDataModel> call, Response<LookUpDataModel> response) {

                                changePassDialog.dismiss();
                                Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<LookUpDataModel> call, Throwable t) {
                                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, progress, wholeView));
            }
        });
    }

}
