package al.ib.lawyer.view.fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.LocaleShared;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyer.LawyerModel;
import al.ib.lawyer.view.register.LawyerRegistration;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LawyerProfileFragment extends Fragment {

    public static final String TAG = "LawyerProfileFragment";
    private LocaleShared localeShared;

    public LawyerProfileFragment() {
        // Required empty public constructor
    }

    private UserManager userManager;
    private User user;

    private TextView txtName, txtDesc, availableTime, address, site, phone, edit, major, majorArbic, fee;
    private ImageView pic;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lawyer_profile, container, false);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();
        localeShared=new LocaleShared(getActivity());
        txtName = view.findViewById(R.id.name);
        txtDesc = view.findViewById(R.id.desc);
        availableTime = view.findViewById(R.id.availableTime);
        fee = view.findViewById(R.id.feeAmount);
        address = view.findViewById(R.id.address);
        site = view.findViewById(R.id.webSite);
        phone = view.findViewById(R.id.telephone);
        edit = view.findViewById(R.id.edit);
        major = view.findViewById(R.id.major);
        majorArbic = view.findViewById(R.id.majorArabic);
        txtName = view.findViewById(R.id.name);
        pic = view.findViewById(R.id.pic);
        progressBar = view.findViewById(R.id.progress);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LawyerRegistration.class);
                intent.putExtra("type", "lawyer");
                startActivity(intent);
            }
        });


        if (user.isLawyer()) {
            edit.setVisibility(View.VISIBLE);

            final JSONObject object = new JSONObject();
            final MediaType mediaType = MediaType.parse("application/raw");
            try {
                object.put("LawyerID", user.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            AndroidNetworking.initialize(getActivity());
            AndroidNetworking.setParserFactory(new JacksonParserFactory());
            AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                    .addJSONObjectBody(object)
                    .addHeaders("Content-Type", "application/json")
                    .addHeaders("MethodName", "GetLawyerByID")
                    .addHeaders("x-user", user.getEmail())
                    .addHeaders("x-pass", user.getPassword())
                    .setPriority(Priority.MEDIUM)
                    .build().getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject result = response.getJSONObject("result");
                        if (result.getString("result").equals("OK")) {
                            JSONArray lawyer = result.getJSONArray("lawyer");
                            JSONObject jsonObject = lawyer.getJSONObject(0);
                            if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                                txtName.setText(jsonObject.getString("FullNameAr"));
                                txtDesc.setText(jsonObject.getString("DescriptionAR"));
                                availableTime.setText(jsonObject.getString("AvailableTimeAR"));
                                address.setText(jsonObject.getString("OfficeAddressAr"));
                                major.setText(jsonObject.getString("MajorcaseDescriptionAR"));
                            } else {
                                txtName.setText(jsonObject.getString("FullNameEn"));
                                txtDesc.setText(jsonObject.getString("DescriptionEn"));
                                availableTime.setText(jsonObject.getString("AvailableTimeEN"));
                                address.setText(jsonObject.getString("OfficeAddressEn"));
                                major.setText(jsonObject.getString("MajorcaseDescriptionEN"));
                            }
                            fee.setText(jsonObject.getString("ConsultationFee"));
                            site.setText(jsonObject.getString("Email"));
                            phone.setText(object.getString("Mobile"));
                            Glide.with(getActivity()).load(jsonObject.getString("pictureURL")).into(pic);
                            localeShared.storeKey("mobile",object.getString("Mobile"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(ANError anError) {

                }
            });

//            Glide.with(getActivity()).load(user.getPictureUrl()).into(pic);
//
//            txtName.setText(user.getFullName() == null ? "" : user.getFullName());
//            txtDesc.setText(user.getDescriptionEn() == null ? "" : user.getDescriptionEn());
//            availableTime.setText(user.getOfficeTimeEn() == null ? "" : user.getOfficeTimeEn());
//            address.setText(user.getOfficeAddressEn() == null ? "" : user.getOfficeAddressEn());
//            site.setText(user.getEmail() == null ? "" : user.getEmail());
//            if (user.getPhone() != null)
//                phone.setText((user.getPhone() == null ? "" : user.getPhone()));
//
//            if (user.getOfficePhone1() != null)
//                phone.setText(user.getOfficePhone1());
//
//            major.setText(user.getMajorCaseEn() == null ? "" : user.getMajorCaseEn());
//            majorArbic.setText(user.getMajorCaseAr() == null ? "" : user.getMajorCaseAr());
//            fee.setText(user.getConsultationFee() == null ? "" : user.getConsultationFee());
        } else {
            edit.setVisibility(View.INVISIBLE);
            String id = getArguments().getString("id");

            JSONObject object = new JSONObject();
            final MediaType mediaType = MediaType.parse("application/raw");
            try {
                object.put("LawyerID", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(mediaType, String.valueOf(object));

            ApiClient.getClient().create(ApiInterface.class).getLawyerByID("GetLawyerByID",
                    user.getEmail(), user.getPassword(),
                    body).enqueue(new RetrofitCallBack<LawyerModel>(getActivity(),
                    new Callback<LawyerModel>() {
                        @Override
                        public void onResponse(Call<LawyerModel> call, Response<LawyerModel> response) {


                            if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                                txtName.setText(response.body().getResult().getLawyer().get(0).getFullNameEn());
                                txtDesc.setText(response.body().getResult().getLawyer().get(0).getDescription());
                                availableTime.setText(response.body().getResult().getLawyer().get(0).getOfficeTimingEn());
                                address.setText(response.body().getResult().getLawyer().get(0).getOfficeAddressEn());
                                major.setText(response.body().getResult().getLawyer().get(0).getMajorcaseDescription());
                                fee.setText(response.body().getResult().getLawyer().get(0).getConsultationFee());
                                Glide.with(getActivity()).load(response.body().getResult().getLawyer().get(0).getPictureURL()).into(pic);

                            }

                            Toast.makeText(getActivity(), response.body().getResult().getDetails()
                                    , Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<LawyerModel> call, Throwable t) {

                        }
                    }, progressBar, view));

        }


        return view;
    }

}
