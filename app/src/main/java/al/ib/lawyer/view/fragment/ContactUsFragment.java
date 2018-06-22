package al.ib.lawyer.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.contactus.ContactUSModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    public static final String TAG = "ContactUsFragment";

    public ContactUsFragment() {
        // Required empty public constructor
    }

    private EditText firstName, lastName, email, phone, comment;
    private TextView send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_us, container, false);

        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.mobile);
        comment = view.findViewById(R.id.comment);
        send = view.findViewById(R.id.send);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Fname", firstName.getText().toString());
            jsonObject.put("Lname", lastName.getText().toString());
            jsonObject.put("Description", comment.getText().toString());
            jsonObject.put("Email", email.getText().toString());
            jsonObject.put("Mobile", phone.getText().toString());
            jsonObject.put("UserId", new UserManager(getActivity()).getUser().getId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType type = MediaType.parse("application/raw");
        final RequestBody body = RequestBody.create(type, jsonObject.toString());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getClient().create(ApiInterface.class).sendContactUs("AddContactUs",
                        body).enqueue(new RetrofitCallBack<>(getActivity(), new Callback<ContactUSModel>() {
                    @Override
                    public void onResponse(Call<ContactUSModel> call, Response<ContactUSModel> response) {
                        Toast.makeText(getActivity(), getString(R.string.messagesent), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<ContactUSModel> call, Throwable t) {

                    }
                }, null, null));
            }
        });
        return view;
    }

}
