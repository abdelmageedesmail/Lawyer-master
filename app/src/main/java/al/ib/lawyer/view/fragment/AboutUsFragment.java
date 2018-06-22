package al.ib.lawyer.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import al.ib.lawyer.R;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.aboutus.AboutUsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    public static final String TAG = "AboutUsFragment";

    public AboutUsFragment() {
        // Required empty public constructor
    }

    private ProgressBar progress;
    private TextView about;
    private ImageView faceBook, twitter, instagram;
    private String faceSite, twitterSite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_about_us, container, false);

        progress = view.findViewById(R.id.progress);
        about = view.findViewById(R.id.about);
        faceBook = view.findViewById(R.id.facebook);
        twitter = view.findViewById(R.id.twitter);
        instagram = view.findViewById(R.id.instagram);

        ApiClient.getClient().create(ApiInterface.class).getAboutUs("GetAboutUsInformation").enqueue(
                new RetrofitCallBack<>(getActivity(), new Callback<AboutUsModel>() {
                    @Override
                    public void onResponse(Call<AboutUsModel> call, Response<AboutUsModel> response) {

                        if (response.isSuccessful()) {
                            about.setText(response.body().getResult().getCustomer().get(0).getLocaleValue());
                            faceSite = response.body().getResult().getCustomer().get(1).getLocaleValue();
                            twitterSite = response.body().getResult().getCustomer().get(2).getLocaleValue();
                        }
                    }

                    @Override
                    public void onFailure(Call<AboutUsModel> call, Throwable t) {

                    }
                }, progress, null)
        );

        faceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = faceSite;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = twitterSite;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;
    }

}
