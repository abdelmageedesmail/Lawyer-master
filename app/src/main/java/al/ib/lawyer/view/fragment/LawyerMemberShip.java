package al.ib.lawyer.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.WebViewActivity;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.knetmodel.KnetModel;
import al.ib.lawyer.model.packagedetails.PackageDetailsModel;
import al.ib.lawyer.model.packagemodel.PackageModel;
import al.ib.lawyer.model.packagemodel.PackagesItem;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LawyerMemberShip extends Fragment {


    public LawyerMemberShip() {
        // Required empty public constructor
    }

    private UserManager userManager;
    private User user;
    private ProgressBar progress;
    private Spinner packegeSpinner;
    List<PackagesItem> list = new ArrayList<>();
    private TextView lawyerName, packageName, packageExpireDate, packageDueDate,
            packageDescription, packagePrice, renew;
    String packageId, price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lawyer_member_ship, container, false);

        progress = view.findViewById(R.id.progress);
        packegeSpinner = view.findViewById(R.id.spinner);
        lawyerName = view.findViewById(R.id.name);
        packageName = view.findViewById(R.id.packageName);
        packageExpireDate = view.findViewById(R.id.expireDate);
        packageDueDate = view.findViewById(R.id.packageDueDate);
        packageDescription = view.findViewById(R.id.description);
        packagePrice = view.findViewById(R.id.price);
        renew = view.findViewById(R.id.renew);
        userManager = new UserManager(getActivity());
        user = userManager.getUser();


        lawyerName.setText(user.getFullName());

        packegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                packageId = String.valueOf(list.get(position).getID());
                price = String.valueOf(list.get(position).getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getAllPackage();

        getLawyerPackageDetails();
        renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renewPackage();
            }
        });
        return view;
    }

    private void getAllPackage() {
        ApiClient.getClient().create(ApiInterface.class).getAllPackageModel("GetAllActivePackages",
                user.getEmail(), user.getPassword()).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<PackageModel>() {
            @Override
            public void onResponse(Call<PackageModel> call, Response<PackageModel> response) {

                List<String> names = new ArrayList<>();
                for (int i = 0; i < response.body().getResult().getPackages().size(); i++) {
                    PackagesItem item = new PackagesItem();
                    item.setID(response.body().getResult().getPackages().get(i).getID());
                    item.setPrice(response.body().getResult().getPackages().get(i).getPrice());
                    item.setKnetURL(response.body().getResult().getPackages().get(i).getKnetURL());
                    item.setIdate(response.body().getResult().getPackages().get(i).getIdate());
                    item.setDescriptionEn(response.body().getResult().getPackages().get(i).getDescriptionEn());
                    item.setDescriptionAr(response.body().getResult().getPackages().get(i).getDescriptionAr());
                    item.setTitleAr(response.body().getResult().getPackages().get(i).getTitleAr());
                    item.setTitleEn(response.body().getResult().getPackages().get(i).getTitleEn());
                    item.setTotalPeriod(response.body().getResult().getPackages().get(i).getTotalPeriod());

                    names.add(response.body().getResult().getPackages().get(i).getTitleEn());
                    list.add(item);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, names);
                packegeSpinner.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PackageModel> call, Throwable t) {

            }
        }, progress, null
        ));
    }

    private void renewPackage() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("LawyerID", user.getLawyerId());
            jsonObject.put("PackageID", packageId);
            jsonObject.put("Price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

        ApiClient.getClient().create(ApiInterface.class).directToKnetUrl("RedirectToKnetURL",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<KnetModel>() {
            @Override
            public void onResponse(Call<KnetModel> call, Response<KnetModel> response) {

                if (response.isSuccessful()) {
                    Intent i = new Intent(getActivity(), WebViewActivity.class);
                    i.putExtra("link",""+response.body().getResult().getRequestDetails().get(0).getKnetURL());
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<KnetModel> call, Throwable t) {

            }
        }, progress, null
        ));
    }

    private void getLawyerPackageDetails() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("LawyerID", user.getLawyerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).getLawyerPackage("GetLawyerActivePackageDetails",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<PackageDetailsModel>() {
            @Override
            public void onResponse(Call<PackageDetailsModel> call, Response<PackageDetailsModel> response) {

                if (response.isSuccessful()) {

                    packageName.setText(response.body().getResult().getQuotations().get(0).getTitleEn());
                    packageDescription.setText(response.body().getResult().getQuotations().get(0).getDescriptionEn());
                    packagePrice.setText(String.valueOf(response.body().getResult().getQuotations().get(0).getPrice()));
                    packageDueDate.setText("Period " + response.body().getResult().getQuotations().get(0).getPeriod());
                    packageExpireDate.setText("Expire Date : " + response.body().getResult().getQuotations().get(0).getExpireDate());
                }
            }

            @Override
            public void onFailure(Call<PackageDetailsModel> call, Throwable t) {

            }
        }, progress, null
        ));
    }

}
