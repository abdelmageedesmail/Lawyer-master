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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import al.ib.lawyer.R;
import al.ib.lawyer.adapter.QuotationAdapter;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.model.QuotationModel;
import al.ib.lawyer.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuotationFragment extends Fragment {


    QuotationAdapter quotationAdapter;
    RecyclerView recyclerView;
    List<QuotationModel> list = new ArrayList<>();
    public static final String TAG = "QuotationFragment";
    private UserManager userManager;
    private User user;

    public QuotationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotation, container, false);

        recyclerView = view.findViewById(R.id.recycler);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();
        getQuations();
        return view;
    }

    private void getQuations(){
        Log.e("userAndPass",user.getEmail()+"..."+user.getPassword());
        AndroidNetworking.initialize(getActivity());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addHeaders("Content-Type","application/json")
                .addHeaders("MethodName","GetallLawyerQuotationByCaseRequestID")
                .addHeaders("x-user",user.getEmail())
                .addHeaders("x-pass",user.getPassword())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject result = response.getJSONObject("result");
                            String result1 = result.getString("result");
                            if (result1.equals("OK")){
                                JSONArray quotations = result.getJSONArray("Quotations");
                                for (int i=0;i<quotations.length();i++){
                                    JSONObject jsonObject = quotations.getJSONObject(i);
                                    QuotationModel model=new QuotationModel();
                                    if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
                                        model.setName(jsonObject.getString("LawyerNameAr"));
                                    }else {
                                        model.setName(jsonObject.getString("LawyerNameEn"));
                                    }
                                    model.setDescription(jsonObject.getString("LawyerDescriptionAr"));
                                    model.setDetails(jsonObject.getString("RDescription"));
                                    list.add(model);
                                }
                                quotationAdapter = new QuotationAdapter(getActivity(), list);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                recyclerView.setAdapter(quotationAdapter);
                                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("error",""+anError.getMessage());
                    }
                });
    }
}
