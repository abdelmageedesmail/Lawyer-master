package al.ib.lawyer.view.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookMeetFragment extends Fragment {


    public static final String TAG = "BookMeetFragment";

    public BookMeetFragment() {
        // Required empty public constructor
    }

    private EditText date, time, description;
    private TextView send, update, cancele;
    private ProgressBar progressBar;
    private UserManager userManager;
    private User user;
    private LinearLayout updateLayout;
    private String meetingID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.book_meet, container, false);

        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        description = view.findViewById(R.id.description);
        send = view.findViewById(R.id.send);
        update = view.findViewById(R.id.update);
        cancele = view.findViewById(R.id.cancele);
        updateLayout = view.findViewById(R.id.updateLayout);
        progressBar = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), TimePickerDialog.THEME_HOLO_DARK,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        final Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH);
        int day = c1.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i + "-" + i1 + "-" + i2);
                    }
                }, year, month, day);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();

                String id = getArguments().getString("id");
                try {
                    jsonObject.put("mdate", date.getText().toString());
                    jsonObject.put("Mtime", time.getText().toString());
                    jsonObject.put("Description", description.getText().toString());
                    jsonObject.put("CustomerID", user.getCustomerId());
                    jsonObject.put("LawyerID", id);
                    jsonObject.put("meetingdtime", time.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());

                ApiClient.getClient().create(ApiInterface.class).addNewMeeting("AddNewMeeting",
                        user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                        getActivity(), new Callback<SimpleModelResponse>() {
                    @Override
                    public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {
                        Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SimpleModelResponse> call, Throwable t) {

                    }
                }, progressBar, null
                ));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("mDate", date.getText().toString());
                    jsonObject.put("mTime", time.getText().toString());
                    jsonObject.put("MeetingID", meetingID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());

                ApiClient.getClient().create(ApiInterface.class).rescheduleMeet("RescheduleMeeting",
                        user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                        getActivity(), new Callback<SimpleModelResponse>() {
                    @Override
                    public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {
                        Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SimpleModelResponse> call, Throwable t) {

                    }
                }, progressBar, null
                ));

            }
        });
        cancele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("MeetingID", meetingID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MediaType type = MediaType.parse("application/raw");
                final RequestBody body = RequestBody.create(type, jsonObject.toString());

                ApiClient.getClient().create(ApiInterface.class).canceleMeet("CancelMeeting",
                        user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                        getActivity(), new Callback<SimpleModelResponse>() {
                    @Override
                    public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {
                        Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SimpleModelResponse> call, Throwable t) {

                    }
                }, progressBar, null
                ));
            }
        });
        if (getArguments().getBoolean("update")){
            updateLayout.setVisibility(View.VISIBLE);
            send.setVisibility(View.GONE);

            date.setText(getArguments().getString("date"));
            time.setText(getArguments().getString("time"));
            description.setText(getArguments().getString("desc"));
            meetingID = getArguments().getString("meeting_id");
        }

        return view;
    }

}
