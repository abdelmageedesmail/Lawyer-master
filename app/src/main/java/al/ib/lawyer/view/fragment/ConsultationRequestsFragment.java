package al.ib.lawyer.view.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.AttachMentAdapter;
import al.ib.lawyer.adapter.RequestHistoryAdapter;
import al.ib.lawyer.adapter.AllOpenRequestAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.interfaces.RequestClicked;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.allrequests.AllRequestsModel;
import al.ib.lawyer.model.allrequests.OpenRequestsItem;
import al.ib.lawyer.model.history.HistoryRequest;
import al.ib.lawyer.model.history.HistoryRequestsItem;
import al.ib.lawyer.model.requestDetails.RequestDetailsItem;
import al.ib.lawyer.model.requestDetails.RequestDetailsModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationRequestsFragment extends Fragment implements RequestClicked {


    AllOpenRequestAdapter adapter;
    RequestHistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    public static final String TAG = "ConsultationRequestsFra";

    private UserManager userManager;
    private User user;
    private Dialog requestDetailsDialog;
    TextView txtDetailsTitle, txtDetailsCaseType, txtDetailsDate, txtDetailsDesc;
    private RecyclerView recyclerAttachmentsDetails;
    ProgressBar progDetails;
    CardView wholeView;
    private int length;

    public ConsultationRequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultation_requests, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView1 = view.findViewById(R.id.recycler1);

        ProgressBar historyProg = view.findViewById(R.id.historyProgress);
        ProgressBar requestsProg = view.findViewById(R.id.requestsProgress);
        userManager = new UserManager(getActivity());
        user = userManager.getUser();


        getOpenRequests(requestsProg);

        gethHistory(historyProg);


        initRequestDetailsDialog();


        return view;
    }

    private void initRequestDetailsDialog() {
        requestDetailsDialog = new Dialog(getActivity());
        requestDetailsDialog.setContentView(R.layout.request_details);

        progDetails = requestDetailsDialog.findViewById(R.id.progress);
        wholeView = requestDetailsDialog.findViewById(R.id.wholeView);
        txtDetailsTitle = requestDetailsDialog.findViewById(R.id.title);
        txtDetailsCaseType = requestDetailsDialog.findViewById(R.id.caseType);
        txtDetailsDate = requestDetailsDialog.findViewById(R.id.date);
        txtDetailsDesc = requestDetailsDialog.findViewById(R.id.desc);
        recyclerAttachmentsDetails = requestDetailsDialog.findViewById(R.id.recycler);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        requestDetailsDialog.getWindow().setLayout(width - 10, height - 10);

    }

    private void getOpenRequests(ProgressBar requestsProg) {
        ApiClient.getClient().create(ApiInterface.class).getAllOpenRequests("GetAllOpenRequests",
                user.getEmail(), user.getPassword()).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<AllRequestsModel>() {
            @Override
            public void onResponse(Call<AllRequestsModel> call, Response<AllRequestsModel> response) {

                List<OpenRequestsItem> list = new ArrayList<>();
                if (response.isSuccessful() && response.body().getResult().getTotalRecord() > 0) {
                    for (int i = 0; i < response.body().getResult().getOpenRequests().size(); i++) {

                        OpenRequestsItem item = new OpenRequestsItem();
                        item.setComments(response.body().getResult().getOpenRequests().get(i).getComments());
                        item.setEntryDate(response.body().getResult().getOpenRequests().get(i).getEntryDate());
                        item.setEntryUserId(response.body().getResult().getOpenRequests().get(i).getEntryUserId());
                        item.setId(response.body().getResult().getOpenRequests().get(i).getId());
                        item.setIsactive(response.body().getResult().getOpenRequests().get(i).isIsactive());
                        item.setIsdeleted(response.body().getResult().getOpenRequests().get(i).isIsdeleted());
                        item.setIsPrivate(response.body().getResult().getOpenRequests().get(i).isIsPrivate());
                        item.setLawyerID(response.body().getResult().getOpenRequests().get(i).getLawyerID());
                        item.setLDate(response.body().getResult().getOpenRequests().get(i).getLDate());
                        item.setLUserId(response.body().getResult().getOpenRequests().get(i).getStatusId());
                        item.setStatusId(response.body().getResult().getOpenRequests().get(i).getStatusId());
                        item.setRType(response.body().getResult().getOpenRequests().get(i).getRType());
                        item.setRTitle(response.body().getResult().getOpenRequests().get(i).getRTitle());
                        item.setRDescription(response.body().getResult().getOpenRequests().get(i).getRDescription());
                        item.setPostDate(response.body().getResult().getOpenRequests().get(i).getPostDate());
                        item.setPictureId(response.body().getResult().getOpenRequests().get(i).getPictureId());

                        list.add(item);
                    }

                    adapter = new AllOpenRequestAdapter(getActivity(), list, ConsultationRequestsFragment.this);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<AllRequestsModel> call, Throwable t) {

            }
        }, requestsProg, null
        ));
    }

    private void gethHistory(ProgressBar historyProg) {
        String header = "";
        String key = "";
        JSONObject jsonObject = new JSONObject();
        try {
            if (userManager.getUser().isLawyer()) {
                header = "GetHistoryRequests_Lawyer";
                jsonObject.put("LawyerID", user.getLawyerId());
            } else {
                header = "GetHistoryRequests_Customer";
                jsonObject.put("CustomerID", user.getCustomerId());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType type = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(type, jsonObject.toString());

        ApiClient.getClient().create(ApiInterface.class).getRequestHistoryLawyer(header,
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(getActivity(),
                new Callback<HistoryRequest>() {
                    @Override
                    public void onResponse(Call<HistoryRequest> call, Response<HistoryRequest> response) {

                        if (response.isSuccessful() && response.body().getResult().getTotalRecord() > 0) {

                            List<HistoryRequestsItem> list = new ArrayList<>();
                            for (int i = 0; i < response.body().getResult().getHistoryRequests().size(); i++) {

                                HistoryRequestsItem item = new HistoryRequestsItem();
                                item.setComments(response.body().getResult().getHistoryRequests().get(i).getComments());
                                item.setEntryDate(response.body().getResult().getHistoryRequests().get(i).getEntryDate());
                                item.setEntryUserId(response.body().getResult().getHistoryRequests().get(i).getEntryUserId());
                                item.setId(response.body().getResult().getHistoryRequests().get(i).getId());
                                item.setIsactive(response.body().getResult().getHistoryRequests().get(i).isIsactive());
                                item.setIsdeleted(response.body().getResult().getHistoryRequests().get(i).isIsdeleted());
                                item.setIsPrivate(response.body().getResult().getHistoryRequests().get(i).isIsPrivate());
                                item.setLawyerID(response.body().getResult().getHistoryRequests().get(i).getLawyerID());
                                item.setLDate(response.body().getResult().getHistoryRequests().get(i).getLDate());
                                item.setLUserId(response.body().getResult().getHistoryRequests().get(i).getStatusId());
                                item.setStatusId(response.body().getResult().getHistoryRequests().get(i).getStatusId());
                                item.setRType(response.body().getResult().getHistoryRequests().get(i).getRType());
                                item.setRTitle(response.body().getResult().getHistoryRequests().get(i).getRTitle());
                                item.setRDescription(response.body().getResult().getHistoryRequests().get(i).getRDescription());
                                item.setPostDate(response.body().getResult().getHistoryRequests().get(i).getPostDate());
                                item.setPictureId(response.body().getResult().getHistoryRequests().get(i).getPictureId());

                                list.add(item);
                            }

                            historyAdapter = new RequestHistoryAdapter(getActivity(), list);
                            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView1.setAdapter(historyAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<HistoryRequest> call, Throwable t) {

                    }
                }, historyProg, null));
    }

    @Override
    public void openRequest(String id) {

        requestDetailsDialog.show();
        requestDetailsDialog.setCancelable(false);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CaseRequestID", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "openRequest: " + id);
//
//        AndroidNetworking.initialize(getActivity());
//        AndroidNetworking.setParserFactory(new JacksonParserFactory());
//        AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
//                .addJSONObjectBody(jsonObject)
//                .addHeaders("Content-Type", "application/raw")
//                .addHeaders("MethodName", "GetRequestDetails")
//                .addHeaders("x-user", user.getEmail())
//                .addHeaders("x-pass", user.getPassword())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("response",""+response);
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.e("error",""+anError.getMessage());
//                    }
//                });

        MediaType type = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(type, jsonObject.toString());
        ApiClient.getClient().create(ApiInterface.class).getRequestDetails("GetRequestDetails", user.getEmail(), user.getPassword(),
                body).enqueue(new RetrofitCallBack<>(getActivity(), new Callback<RequestDetailsModel>() {
            @Override
            public void onResponse(Call<RequestDetailsModel> call, Response<RequestDetailsModel> response) {
                requestDetailsDialog.setCancelable(true);
                if (response.isSuccessful()) {
                    List<RequestDetailsItem> list = new ArrayList<>();
                    if (response.body().getResult().getRequestDetails().size()>=2){
                        length=2;
                    }else{
                        length=response.body().getResult().getRequestDetails().size();
                    }
                    for (int i = 0; i < length; i++) {
                        RequestDetailsItem item = new RequestDetailsItem();
                        item.setDownloadUrl(response.body().getResult().getRequestDetails().get(i).getDownloadUrl());
                        item.setDownloadBinary(response.body().getResult().getRequestDetails().get(i).getDownloadBinary());
                        item.setDownloadGuid(response.body().getResult().getRequestDetails().get(i).getDownloadGuid());
                        item.setExtension(response.body().getResult().getRequestDetails().get(i).getExtension());
                        item.setFilename(response.body().getResult().getRequestDetails().get(i).getFilename());
                        list.add(item);
                    }
                    txtDetailsTitle.setText(response.body().getResult().getRequestDetails().get(0).getRTitle());
//                    txtDetailsCaseType.setText(response.body().getResult().getRequestDetails().get(0).getRType());
                    txtDetailsDate.setText(response.body().getResult().getRequestDetails().get(0).getPostDate());
                    txtDetailsDesc.setText(response.body().getResult().getRequestDetails().get(0).getRDescription());
                    recyclerAttachmentsDetails.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    recyclerAttachmentsDetails.setAdapter(new AttachMentAdapter(getActivity(), list));

                }
            }

            @Override
            public void onFailure(Call<RequestDetailsModel> call, Throwable t) {
                requestDetailsDialog.setCancelable(true);
            }
        }, progDetails, wholeView));
    }
}
