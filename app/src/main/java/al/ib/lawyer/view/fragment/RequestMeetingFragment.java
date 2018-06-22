package al.ib.lawyer.view.fragment;


import android.content.res.ColorStateList;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.RequestMeetingFragmentAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.SecheduledMeetings;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.cscanclemeeting.CanceledMeetingModel;
import al.ib.lawyer.model.customermeeting.CustomerMeetingModel;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingItem;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestMeetingFragment extends Fragment implements View.OnClickListener {

    RequestMeetingFragmentAdapter adapter;
    RecyclerView recyclerView;
    public static final String TAG = "RequestMeetingFragment";

    public RequestMeetingFragment() {
        // Required empty public constructor
    }


    private UserManager userManager;
    private User user;
    private ProgressBar progress;

    private TextView upComing, rescheduled, canceled;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_meeting, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        upComing = view.findViewById(R.id.upComing);
        rescheduled = view.findViewById(R.id.rescheduled);
        canceled = view.findViewById(R.id.canceled);
        progress = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        upComing.setOnClickListener(this);
        rescheduled.setOnClickListener(this);
        canceled.setOnClickListener(this);

        if (user.isLawyer()) {
            getLawyerMeetings();

        } else {
            getCustomerMeetings();
            rescheduled.setVisibility(View.GONE);
        }


        return view;
    }

    private void getCustomerMeetings() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CustomerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).getCustomerMeetings("GetMyMeeting_customer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<CustomerMeetingModel>(
                getActivity(), new Callback<CustomerMeetingModel>() {
            @Override
            public void onResponse(Call<CustomerMeetingModel> call, Response<CustomerMeetingModel> response) {

                Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getResult());
                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                    List<LawyerMeetingItem> list = new ArrayList<>();
                    if (response.body().getResult().getTotalRecord() > 0) {
                        for (int i = 0; i < response.body().getResult().getCustomerMeeting().size(); i++) {

                            LawyerMeetingItem item = new LawyerMeetingItem();

                            item.setComment(response.body().getResult().getCustomerMeeting().get(i).getComment());
                            item.setCustomerID(response.body().getResult().getCustomerMeeting().get(i).getCustomerID());
                            item.setCustomerNameAr(response.body().getResult().getCustomerMeeting().get(i).getCustomerNameAr());
                            item.setCustomerNameEn(response.body().getResult().getCustomerMeeting().get(i).getCustomerNameEn());
                            item.setDescription(response.body().getResult().getCustomerMeeting().get(i).getDescription());
                            item.setEntryDate(response.body().getResult().getCustomerMeeting().get(i).getEntryDate());
                            item.setID(response.body().getResult().getCustomerMeeting().get(i).getID());
                            item.setIsActive(response.body().getResult().getCustomerMeeting().get(i).isIsActive());
                            item.setStatusID(response.body().getResult().getCustomerMeeting().get(i).getStatusID());
                            item.setStatusEn(response.body().getResult().getCustomerMeeting().get(i).getStatusEn());
                            item.setStatusAr(response.body().getResult().getCustomerMeeting().get(i).getStatusAr());
                            item.setMtime(response.body().getResult().getCustomerMeeting().get(i).getMtime());
                            item.setMdate(response.body().getResult().getCustomerMeeting().get(i).getMdate());
                            item.setLUserId(response.body().getResult().getCustomerMeeting().get(i).getLUserId());
                            item.setLDate(response.body().getResult().getCustomerMeeting().get(i).getLDate());
                            item.setLawyerNameEn(response.body().getResult().getCustomerMeeting().get(i).getLawyerNameEn());
                            item.setLawyerNameAr(response.body().getResult().getCustomerMeeting().get(i).getLawyerNameAr());
                            item.setLawyerID(response.body().getResult().getCustomerMeeting().get(i).getLawyerID());
                            item.setIsdeleted(response.body().getResult().getCustomerMeeting().get(i).isIsdeleted());
                            list.add(item);
                        }

                    }
                    adapter = new RequestMeetingFragmentAdapter(getActivity(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                } else {
                    Toast.makeText(getActivity(), "No data to display", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerMeetingModel> call, Throwable t) {

            }
        }, progress, null));


    }

    private void getScheduledMeetings() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LawyerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).getLawyerScheduledMeeting("GetUpComingReschudleMeeting_Lawyer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<SecheduledMeetings>() {
                    @Override
                    public void onResponse(Call<SecheduledMeetings> call, Response<SecheduledMeetings> response) {

                        List<LawyerMeetingItem> list = new ArrayList<>();


                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getResult());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                            Log.d(TAG, "onResponse: " + response.body().getResult().getUpComingCancelledMeeting());
                            if (response.body().getResult().getTotalRecord() > 0) {
                                for (int i = 0; i < response.body().getResult().getUpComingCancelledMeeting().size(); i++) {
                                    LawyerMeetingItem item = new LawyerMeetingItem();

                                    item.setComment(response.body().getResult().getUpComingCancelledMeeting().get(i).getComment());
                                    item.setCustomerFullNameAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerFullNameAr());
                                    item.setCustomerFullNameEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerFullNameEn());
                                    item.setCustomerID(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerID());
                                    item.setCustomerNameAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerNameAr());
                                    item.setCustomerNameEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerNameEn());
                                    item.setDescription(response.body().getResult().getUpComingCancelledMeeting().get(i).getDescription());
                                    item.setEntryDate(response.body().getResult().getUpComingCancelledMeeting().get(i).getEntryDate());
                                    item.setID(response.body().getResult().getUpComingCancelledMeeting().get(i).getID());
                                    item.setIsActive(response.body().getResult().getUpComingCancelledMeeting().get(i).isIsActive());
                                    item.setStatusID(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusID());
                                    item.setStatusEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusEn());
                                    item.setStatusAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusAr());
                                    item.setMtime(response.body().getResult().getUpComingCancelledMeeting().get(i).getMtime());
                                    item.setMdate(response.body().getResult().getUpComingCancelledMeeting().get(i).getMdate());
                                    item.setLUserId(response.body().getResult().getUpComingCancelledMeeting().get(i).getLUserId());
                                    item.setLDate(response.body().getResult().getUpComingCancelledMeeting().get(i).getLDate());
                                    item.setLawyerNameEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerNameEn());
                                    item.setLawyerNameAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerNameAr());
                                    item.setLawyerID(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerID());
                                    item.setIsdeleted(response.body().getResult().getUpComingCancelledMeeting().get(i).isIsdeleted());
                                    list.add(item);
                                }

                            }
                            adapter = new RequestMeetingFragmentAdapter(getActivity(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                        } else {
                            Toast.makeText(getActivity(), "No data to display", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SecheduledMeetings> call, Throwable t) {

                    }
                }, progress, null));
    }

    private void getLawyerMeetings() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LawyerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).getLawyerComingMeeting("GetMyMeeting_Lawyer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<al.ib.lawyer.model.meeting.LawyerMeetingModel>() {
                    @Override
                    public void onResponse(Call<al.ib.lawyer.model.meeting.LawyerMeetingModel> call, Response<al.ib.lawyer.model.meeting.LawyerMeetingModel> response) {

                        List<LawyerMeetingItem> list = new ArrayList<>();


                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                            Log.d(TAG, "onResponse: " + response.body().getResult().getLawyerMeeting());
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
                            adapter = new RequestMeetingFragmentAdapter(getActivity(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                        }
                    }

                    @Override
                    public void onFailure(Call<al.ib.lawyer.model.meeting.LawyerMeetingModel> call, Throwable t) {

                    }
                }, progress, null));
    }

    private void getCustomerCanceledMeetings() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CustomerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).getCustomerCanceledMeetings("GetUpComingCancelledMeeting_Customer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<CanceledMeetingModel>(
                getActivity(), new Callback<CanceledMeetingModel>() {
            @Override
            public void onResponse(Call<CanceledMeetingModel> call, Response<CanceledMeetingModel> response) {

                Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getResult());
                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                    List<LawyerMeetingItem> list = new ArrayList<>();
                    if (response.body().getResult().getTotalRecord() > 0) {
                        for (int i = 0; i < response.body().getResult().getUpComingCancelledMeeting().size(); i++) {

                            LawyerMeetingItem item = new LawyerMeetingItem();

                            item.setComment(response.body().getResult().getUpComingCancelledMeeting().get(i).getComment());
                            item.setCustomerID(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerID());
                            item.setCustomerNameAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerNameAr());
                            item.setCustomerNameEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getCustomerNameEn());
                            item.setDescription(response.body().getResult().getUpComingCancelledMeeting().get(i).getDescription());
                            item.setEntryDate(response.body().getResult().getUpComingCancelledMeeting().get(i).getEntryDate());
                            item.setID(response.body().getResult().getUpComingCancelledMeeting().get(i).getID());
                            item.setIsActive(response.body().getResult().getUpComingCancelledMeeting().get(i).isIsActive());
                            item.setStatusID(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusID());
                            item.setStatusEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusEn());
                            item.setStatusAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getStatusAr());
                            item.setMtime(response.body().getResult().getUpComingCancelledMeeting().get(i).getMtime());
                            item.setMdate(response.body().getResult().getUpComingCancelledMeeting().get(i).getMdate());
                            item.setLUserId(response.body().getResult().getUpComingCancelledMeeting().get(i).getLUserId());
                            item.setLDate(response.body().getResult().getUpComingCancelledMeeting().get(i).getLDate());
                            item.setLawyerNameEn(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerNameEn());
                            item.setLawyerNameAr(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerNameAr());
                            item.setLawyerID(response.body().getResult().getUpComingCancelledMeeting().get(i).getLawyerID());
                            item.setIsdeleted(response.body().getResult().getUpComingCancelledMeeting().get(i).isIsdeleted());
                            list.add(item);
                        }

                    }
                    adapter = new RequestMeetingFragmentAdapter(getActivity(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                } else {
                    Toast.makeText(getActivity(), "No data to display", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CanceledMeetingModel> call, Throwable t) {

            }
        }, progress, null));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.upComing:
                upComing.setBackground(getActivity().getResources().getDrawable(R.drawable.curvy_bg));
                upComing.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.blue)));
                upComing.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                rescheduled.setBackground(null);
                canceled.setBackground(null);
                rescheduled.setTextColor(getActivity().getResources().getColor(R.color.black));
                canceled.setTextColor(getActivity().getResources().getColor(R.color.black));
                if (user.isLawyer())
                    getLawyerMeetings();
                else getCustomerMeetings();
                break;
            case R.id.rescheduled:
                rescheduled.setBackground(getActivity().getResources().getDrawable(R.drawable.curvy_bg));
                rescheduled.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.blue)));
                rescheduled.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                upComing.setBackground(null);
                canceled.setBackground(null);
                upComing.setTextColor(getActivity().getResources().getColor(R.color.black));
                canceled.setTextColor(getActivity().getResources().getColor(R.color.black));
                getScheduledMeetings();
                break;
            case R.id.canceled:

                canceled.setBackground(getActivity().getResources().getDrawable(R.drawable.curvy_bg));
                canceled.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.blue)));
                canceled.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                upComing.setBackground(null);
                rescheduled.setBackground(null);
                rescheduled.setTextColor(getActivity().getResources().getColor(R.color.black));
                upComing.setTextColor(getActivity().getResources().getColor(R.color.black));
                if (user.isLawyer()) {

                } else {
                    getCustomerCanceledMeetings();
                }
                break;
        }
    }
}
