package al.ib.lawyer.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.LawyersAdapter;
import al.ib.lawyer.adapter.QuotationAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyers.LawyerItem;
import al.ib.lawyer.model.lawyers.LawyersModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LawyerFragment extends Fragment {

    LawyersAdapter lawyersAdapter;
    RecyclerView recyclerView;
    private UserManager userManager;
    private User user;
    private EditText txtLawyerName;
    private ProgressBar progress;
    private TextView search;

    public static final String TAG = "LawyerFragment";

    public LawyerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lawyer, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        txtLawyerName = view.findViewById(R.id.name);
        progress = view.findViewById(R.id.progress);
        search = view.findViewById(R.id.search);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        getLawyers();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLawyers();
            }
        });


        return view;
    }

    private void getLawyers() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Name", txtLawyerName.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).getLawyers("Searchlawyer",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<LawyersModel>(
                getActivity(), new Callback<LawyersModel>() {
            @Override
            public void onResponse(Call<LawyersModel> call, Response<LawyersModel> response) {

                if (response.isSuccessful()) {
                    List<LawyerItem> list = new ArrayList<>();

                    for (int i = 0; i < response.body().getResult().getLawyer().size(); i++) {

                        LawyerItem item = new LawyerItem();
                        item.setActive(response.body().getResult().getLawyer().get(i).isActive());
                        item.setBinaryImage(response.body().getResult().getLawyer().get(i).getBinaryImage());
                        item.setConsultationFee(response.body().getResult().getLawyer().get(i).getConsultationFee());
                        item.setCreatedOnUtc(response.body().getResult().getLawyer().get(i).getCreatedOnUtc());
                        item.setDeleted(response.body().getResult().getLawyer().get(i).isDeleted());
                        item.setPictureURL(response.body().getResult().getLawyer().get(i).getPictureURL());
                        item.setPictureId(response.body().getResult().getLawyer().get(i).getPictureId());
                        item.setPhone1(response.body().getResult().getLawyer().get(i).getPhone1());
                        item.setPassword(response.body().getResult().getLawyer().get(i).getPassword());
                        item.setOfficeTimingEn(response.body().getResult().getLawyer().get(i).getOfficeTimingEn());
                        item.setOfficeTimingAr(response.body().getResult().getLawyer().get(i).getOfficeTimingAr());
                        item.setOfficePhone2(response.body().getResult().getLawyer().get(i).getOfficePhone2());
                        item.setOfficePhone1(response.body().getResult().getLawyer().get(i).getOfficePhone1());
                        item.setOfficeAddressEn(response.body().getResult().getLawyer().get(i).getOfficeAddressEn());
                        item.setOfficeAddressAr(response.body().getResult().getLawyer().get(i).getOfficeAddressAr());
                        item.setMIMeType(response.body().getResult().getLawyer().get(i).getMIMeType());
                        item.setMemberNo(response.body().getResult().getLawyer().get(i).getMemberNo());
                        item.setMajorcaseDescriptionAr(response.body().getResult().getLawyer().get(i).getMajorcaseDescriptionAr());
                        item.setLawyerID(response.body().getResult().getLawyer().get(i).getLawyerID());
                        item.setLastLoginDateUtc(response.body().getResult().getLawyer().get(i).getLastLoginDateUtc());
                        item.setLastActivityDateUtc(response.body().getResult().getLawyer().get(i).getLastActivityDateUtc());
                        item.setKsalt(response.body().getResult().getLawyer().get(i).getKsalt());
                        item.setIsLawyer(response.body().getResult().getLawyer().get(i).isIsLawyer());
                        item.setGender(response.body().getResult().getLawyer().get(i).getGender());
                        item.setFullNameEn(response.body().getResult().getLawyer().get(i).getFullNameEn());
                        item.setFullNameAr(response.body().getResult().getLawyer().get(i).getFullNameAr());
                        item.setEmail(response.body().getResult().getLawyer().get(i).getEmail());
                        item.setDescriptionAR(response.body().getResult().getLawyer().get(i).getDescriptionAR());
                        item.setDescription(response.body().getResult().getLawyer().get(i).getDescription());

                        list.add(item);
                    }
                    if (list.size()>0){
                        Collections.reverse(list);
                        lawyersAdapter = new LawyersAdapter(getActivity(), list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(lawyersAdapter);
//                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                    }

                }
            }

            @Override
            public void onFailure(Call<LawyersModel> call, Throwable t) {

            }
        }, progress, null
        ));
    }

}
