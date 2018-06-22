package al.ib.lawyer.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.MeetingAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingItem;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingsFragment extends Fragment {


    private RecyclerView recyclerView;
    private MeetingAdapter meetingAdapter;
    public static final String TAG = "MeetingsFragment";

    public MeetingsFragment() {
        // Required empty public constructor
    }

    private UserManager userManager;
    private User user;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meetings, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        progress = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();


        JSONObject jsonObject = new JSONObject();
        String header = "";
        try {

            if (user.isLawyer()) {

                header = "GetMyOpenRequests_Lawyer";
                jsonObject.put("LawyerID", user.getLawyerId());
            } else {
                header = "GetMyOpenRequests_customer";
                jsonObject.put("CustomerID", user.getCustomerId());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));


        ApiClient.getClient().create(ApiInterface.class).getLawyerOpenRequests(header,
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<LawyerMeetingModel>() {
                    @Override
                    public void onResponse(Call<LawyerMeetingModel> call, Response<LawyerMeetingModel> response) {

                        List<LawyerMeetingItem> list = new ArrayList<>();

                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getResult());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                            if (response.body().getResult().getTotalRecord() > 0) {
                                for (int i = 0; i < response.body().getResult().getLawyerMeeting().size(); i++) {
                                    LawyerMeetingItem item = new LawyerMeetingItem();

                                    item.setComment(response.body().getResult().getLawyerMeeting().get(i).getComment());
                                    item.setCustomerFullNameAr(response.body().getResult().getLawyerMeeting().get(i).getCustomerFullNameAr());
                                    item.setCustomerFullNameEn(response.body().getResult().getLawyerMeeting().get(i).getCustomerFullNameEn());
                                    item.setCustomerID(response.body().getResult().getLawyerMeeting().get(i).getCustomerID());
                                    item.setCustomerNameAr(response.body().getResult().getLawyerMeeting().get(i).getCustomerNameAr());
                                    item.setCustomerNameEn(response.body().getResult().getLawyerMeeting().get(i).getCustomerNameEn());
                                    item.setDescription(response.body().getResult().getLawyerMeeting().get(i).getDescription());
                                    item.setEntryDate(response.body().getResult().getLawyerMeeting().get(i).getEntryDate());
                                    item.setID(response.body().getResult().getLawyerMeeting().get(i).getID());
                                    item.setIsActive(response.body().getResult().getLawyerMeeting().get(i).isIsActive());
                                    item.setStatusID(response.body().getResult().getLawyerMeeting().get(i).getStatusID());
                                    item.setStatusEn(response.body().getResult().getLawyerMeeting().get(i).getStatusEn());
                                    item.setStatusAr(response.body().getResult().getLawyerMeeting().get(i).getStatusAr());
                                    item.setMtime(response.body().getResult().getLawyerMeeting().get(i).getMtime());
                                    item.setMdate(response.body().getResult().getLawyerMeeting().get(i).getMdate());
                                    item.setLUserId(response.body().getResult().getLawyerMeeting().get(i).getLUserId());
                                    item.setLDate(response.body().getResult().getLawyerMeeting().get(i).getLDate());
                                    item.setLawyerNameEn(response.body().getResult().getLawyerMeeting().get(i).getLawyerNameEn());
                                    item.setLawyerNameAr(response.body().getResult().getLawyerMeeting().get(i).getLawyerNameAr());
                                    item.setLawyerID(response.body().getResult().getLawyerMeeting().get(i).getLawyerID());
                                    item.setIsdeleted(response.body().getResult().getLawyerMeeting().get(i).isIsdeleted());
                                    list.add(item);
                                }
                                Log.d(TAG, "onResponse: " + list);
                            }
                            meetingAdapter = new MeetingAdapter(getActivity(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(meetingAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                        }
                    }

                    @Override
                    public void onFailure(Call<LawyerMeetingModel> call, Throwable t) {

                    }
                }, progress, null));


        return view;
    }

}
