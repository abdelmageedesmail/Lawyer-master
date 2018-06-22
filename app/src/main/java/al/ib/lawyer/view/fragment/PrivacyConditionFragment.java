package al.ib.lawyer.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import al.ib.lawyer.R;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.privacymodel.PrivacyConditionItem;
import al.ib.lawyer.model.privacymodel.PrivacyModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyConditionFragment extends Fragment {


    public static final String TAG = "PrivacyConditionFragmen";

    public PrivacyConditionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_condition, container, false);

        final TextView body = view.findViewById(R.id.body);
        ProgressBar progressBar = view.findViewById(R.id.progress);
        CardView wholeView = view.findViewById(R.id.wholeView);

        ApiClient.getClient().create(ApiInterface.class).getPrivacy("GetPrivacyCondition")
                .enqueue(new RetrofitCallBack<>(getActivity(),
                        new Callback<PrivacyModel>() {
                            @Override
                            public void onResponse(Call<PrivacyModel> call, Response<PrivacyModel> response) {

                                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {
                                    body.setText(response.body().getResult().getPrivacyCondition().get(0).getLocaleValue());
                                }
                            }

                            @Override
                            public void onFailure(Call<PrivacyModel> call, Throwable t) {

                            }
                        }, progressBar, wholeView));
        return view;
    }

}
