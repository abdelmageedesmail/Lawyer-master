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
import al.ib.lawyer.adapter.PrivateRequestsAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.privaterequest.PrivateRequestModel;
import al.ib.lawyer.model.privaterequest.PrivateRequestsItem;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateRequestFragment extends Fragment {


    public static final String TAG = "PrivateRequestFragment";
    PrivateRequestsAdapter openRequestsAdapter;
    RecyclerView recyclerView;
    List<PrivateRequestsItem> list = new ArrayList<>();

    public PrivateRequestFragment() {
        // Required empty public constructor
    }

    private UserManager userManager;
    private User user;
    private ProgressBar progressBar;
    private CardView wholeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.private_requests, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        progressBar = view.findViewById(R.id.progress);
        wholeView = view.findViewById(R.id.wholeView);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LawyerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).getPrivateRequestsLawyer("GetMyPrivateRequests_Lawyer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<PrivateRequestModel>() {
                    @Override
                    public void onResponse(Call<PrivateRequestModel> call, Response<PrivateRequestModel> response) {

                        Log.d(TAG, "onResponse: " + response.isSuccessful() + ", " + response.body().getResult().getTotalRecord() +", "
                        + response.body().getResult());
                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK") &&
                                response.body().getResult().getTotalRecord() > 0 &&
                                response.body().getResult().getPrivateRequests().size() > 0) {

                            for (int i = 0; i < response.body().getResult().getPrivateRequests().size(); i++) {

                                PrivateRequestsItem model = new PrivateRequestsItem();
                                model.setComments(response.body().getResult().getPrivateRequests().get(i).getComments());
                                model.setEntryDate(response.body().getResult().getPrivateRequests().get(i).getEntryDate());
                                model.setEntryUserId(response.body().getResult().getPrivateRequests().get(i).getEntryUserId());
                                model.setId(response.body().getResult().getPrivateRequests().get(i).getId());
                                model.setIsactive(response.body().getResult().getPrivateRequests().get(i).isIsactive());
                                model.setIsdeleted(response.body().getResult().getPrivateRequests().get(i).isIsdeleted());
                                model.setIsPrivate(response.body().getResult().getPrivateRequests().get(i).isIsPrivate());
                                model.setLDate(response.body().getResult().getPrivateRequests().get(i).getLDate());
                                model.setLawyerID(response.body().getResult().getPrivateRequests().get(i).getLawyerID());
                                model.setStatusId(response.body().getResult().getPrivateRequests().get(i).getStatusId());
                                model.setRTitle(response.body().getResult().getPrivateRequests().get(i).getRTitle());
                                model.setRType(response.body().getResult().getPrivateRequests().get(i).getRType());
                                model.setRDescription(response.body().getResult().getPrivateRequests().get(i).getRDescription());
                                model.setPictureId(response.body().getResult().getPrivateRequests().get(i).getPictureId());
                                model.setPostDate(response.body().getResult().getPrivateRequests().get(i).getPostDate());
                                model.setLUserId(response.body().getResult().getPrivateRequests().get(i).getLUserId());

                                list.add(model);
                            }

                            openRequestsAdapter = new PrivateRequestsAdapter(getActivity(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(openRequestsAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                        } else
                            Toast.makeText(getActivity(), "No Requests to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PrivateRequestModel> call, Throwable t) {

                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                }, progressBar, null));


        return view;
    }

}
