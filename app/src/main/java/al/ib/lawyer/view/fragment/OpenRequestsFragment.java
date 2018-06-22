package al.ib.lawyer.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.MyOpenRequestCustomerAdapter;
import al.ib.lawyer.adapter.OpenRequestsAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyerrequests.LawyerOpenRequestsModel;
import al.ib.lawyer.model.lawyerrequests.OpenRequestsItem;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenRequestsFragment extends Fragment implements MyOpenRequestCustomerAdapter.CustomerOpenRequests {

    OpenRequestsAdapter openRequestsAdapter;
    RecyclerView recyclerView;
    List<OpenRequestsItem> list = new ArrayList<>();
    public static final String TAG = "OpenRequestsFragment";

    public OpenRequestsFragment() {
        // Required empty public constructor
    }


    private UserManager userManager;
    private User user;
    private ProgressBar progressBar;
    private CardView wholeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_requests, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        progressBar = view.findViewById(R.id.progress);
        wholeView = view.findViewById(R.id.wholeView);

        if (user.isLawyer())
            getLawyerOpenRequests();
        else getCustomerOpenRequest();


        return view;
    }

    private void getCustomerOpenRequest() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CustomerID", user.getCustomerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).lawyerOpenRequests("GetMyOpenRequests_customer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<LawyerOpenRequestsModel>() {
                    @Override
                    public void onResponse(Call<LawyerOpenRequestsModel> call, Response<LawyerOpenRequestsModel> response) {

                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getTotalRecord());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                            for (int i = 0; i < response.body().getResult().getOpenRequests().size(); i++) {

                                OpenRequestsItem model = new OpenRequestsItem();
                                model.setComments(response.body().getResult().getOpenRequests().get(i).getComments());
                                model.setEntryDate(response.body().getResult().getOpenRequests().get(i).getEntryDate());
                                model.setEntryUserId(response.body().getResult().getOpenRequests().get(i).getEntryUserId());
                                model.setId(response.body().getResult().getOpenRequests().get(i).getId());
                                model.setIsactive(response.body().getResult().getOpenRequests().get(i).isIsactive());
                                model.setIsdeleted(response.body().getResult().getOpenRequests().get(i).isIsdeleted());
                                model.setIsPrivate(response.body().getResult().getOpenRequests().get(i).isIsPrivate());
                                model.setLDate(response.body().getResult().getOpenRequests().get(i).getLDate());
                                model.setLawyerID(response.body().getResult().getOpenRequests().get(i).getLawyerID());
                                model.setStatusId(response.body().getResult().getOpenRequests().get(i).getStatusId());
                                model.setRTitle(response.body().getResult().getOpenRequests().get(i).getRTitle());
                                model.setRType(response.body().getResult().getOpenRequests().get(i).getRType());
                                model.setRDescription(response.body().getResult().getOpenRequests().get(i).getRDescription());
                                model.setPictureId(response.body().getResult().getOpenRequests().get(i).getPictureId());
                                model.setPostDate(response.body().getResult().getOpenRequests().get(i).getPostDate());
                                model.setLUserId(response.body().getResult().getOpenRequests().get(i).getLUserId());

                                list.add(model);
                            }

                            MyOpenRequestCustomerAdapter openRequestsAdapter = new MyOpenRequestCustomerAdapter(getActivity(), list, OpenRequestsFragment.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(openRequestsAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                        } else
                            Toast.makeText(getActivity(), "No Requests to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LawyerOpenRequestsModel> call, Throwable t) {

                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                }, progressBar, null));
    }

    private void getLawyerOpenRequests() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LawyerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).lawyerOpenRequests("GetMyOpenRequests_Lawyer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<LawyerOpenRequestsModel>() {
                    @Override
                    public void onResponse(Call<LawyerOpenRequestsModel> call, Response<LawyerOpenRequestsModel> response) {

                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getTotalRecord());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {

                            for (int i = 0; i < response.body().getResult().getOpenRequests().size(); i++) {

                                OpenRequestsItem model = new OpenRequestsItem();
                                model.setComments(response.body().getResult().getOpenRequests().get(i).getComments());
                                model.setEntryDate(response.body().getResult().getOpenRequests().get(i).getEntryDate());
                                model.setEntryUserId(response.body().getResult().getOpenRequests().get(i).getEntryUserId());
                                model.setId(response.body().getResult().getOpenRequests().get(i).getId());
                                model.setIsactive(response.body().getResult().getOpenRequests().get(i).isIsactive());
                                model.setIsdeleted(response.body().getResult().getOpenRequests().get(i).isIsdeleted());
                                model.setIsPrivate(response.body().getResult().getOpenRequests().get(i).isIsPrivate());
                                model.setLDate(response.body().getResult().getOpenRequests().get(i).getLDate());
                                model.setLawyerID(response.body().getResult().getOpenRequests().get(i).getLawyerID());
                                model.setStatusId(response.body().getResult().getOpenRequests().get(i).getStatusId());
                                model.setRTitle(response.body().getResult().getOpenRequests().get(i).getRTitle());
                                model.setRType(response.body().getResult().getOpenRequests().get(i).getRType());
                                model.setRDescription(response.body().getResult().getOpenRequests().get(i).getRDescription());
                                model.setPictureId(response.body().getResult().getOpenRequests().get(i).getPictureId());
                                model.setPostDate(response.body().getResult().getOpenRequests().get(i).getPostDate());
                                model.setLUserId(response.body().getResult().getOpenRequests().get(i).getLUserId());

                                list.add(model);
                            }

                            openRequestsAdapter = new OpenRequestsAdapter(getActivity(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(openRequestsAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                        }
                    }

                    @Override
                    public void onFailure(Call<LawyerOpenRequestsModel> call, Throwable t) {

                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                }, progressBar, null));
    }

    @Override
    public void deleteClicked(String id) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("MeetingID", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).updateCaseRequest("DeleteMeeting",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<SimpleModelResponse>() {
            @Override
            public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {

                Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {
                    getCustomerOpenRequest();
                }
            }

            @Override
            public void onFailure(Call<SimpleModelResponse> call, Throwable t) {

            }
        }, progressBar, null));
    }
}
