package al.ib.lawyer.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.calimmodel.ClaimModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClaimsAndComplainsFragment extends Fragment {


    public static final String TAG = "ClaimsAndComplainsFragm";

    public ClaimsAndComplainsFragment() {
        // Required empty public constructor
    }

    private TextView txtFirstName, txtLastName, txtEmail, txtMobile, txtComment, txtSend;
    private UserManager userManager;
    private User user;
    private ProgressBar progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.claims_complain, container, false);

        txtFirstName = view.findViewById(R.id.firstName);
        txtLastName = view.findViewById(R.id.lastName);
        txtEmail = view.findViewById(R.id.email);
        txtMobile = view.findViewById(R.id.mobile);
        txtComment = view.findViewById(R.id.comment);
        txtSend = view.findViewById(R.id.send);
        progress = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        txtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("Fname", txtFirstName.getText().toString());
                    jsonObject.put("Lname", txtLastName.getText().toString());
                    jsonObject.put("Description", txtComment.getText().toString());
                    jsonObject.put("Email", txtEmail.getText().toString());
                    jsonObject.put("Mobile", txtMobile.getText().toString());
                    jsonObject.put("UserId", user.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());
                ApiClient.getClient().create(ApiInterface.class).sendClaimAndComplain("AddNewClaim",
                        user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<ClaimModel>(
                        getActivity(), new Callback<ClaimModel>() {
                    @Override
                    public void onResponse(Call<ClaimModel> call, Response<ClaimModel> response) {

                        Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ClaimModel> call, Throwable t) {

                    }
                }, progress, null
                ));
            }
        });

        return view;
    }

}
