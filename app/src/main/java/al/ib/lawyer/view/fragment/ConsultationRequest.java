package al.ib.lawyer.view.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.addrequest.AddRequestModel;
import al.ib.lawyer.model.caserequest.CaseRequestModel;
import al.ib.lawyer.model.lookupdata.CaseTypeItem;
import al.ib.lawyer.model.lookupdata.LookUpDataModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationRequest extends Fragment {


    public static final String TAG = "ConsultationRequest";
    private static final int REQUEST_CODE = 55;
    private String serviceId;
    private int caseId;

    public ConsultationRequest() {
        // Required empty public constructor
    }


    private Spinner spinner;
    private EditText title, description;
    private TextView send, browse, tvFileTitle;
    private UserManager userManager;
    private User user;
    private ProgressBar progress;
    private String id;
    private File file;
    private Bitmap bm;
    private Uri selectedImageUri;
    private String selectedImagePath,substring;
    private String imagepath;
    private File imageFile;
    int SELECT_FILE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consultation_send_request, container, false);

        spinner = view.findViewById(R.id.type);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        send = view.findViewById(R.id.send);
        browse = view.findViewById(R.id.browse);
        tvFileTitle = view.findViewById(R.id.attachedFile);
        progress = view.findViewById(R.id.progress);
        setUpPermissions();
        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        final List<CaseTypeItem> list = new ArrayList<>();

        if (getArguments() != null)
            Toast.makeText(getActivity(), "You can Update now", Toast.LENGTH_SHORT).show();
        ApiClient.getClient().create(ApiInterface.class).getLookUpData("GetLookUpData",
                user.getEmail(), user.getPassword()).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<LookUpDataModel>() {
            @Override
            public void onResponse(Call<LookUpDataModel> call, Response<LookUpDataModel> response) {

                if (response.isSuccessful()) {

                    List<String> names = new ArrayList<>();
                    for (int i = 0; i < response.body().getResult().getLookUP().getCaseType().size(); i++) {
                        CaseTypeItem item = new CaseTypeItem();
                        item.setTypeNameAr(response.body().getResult().getLookUP().getCaseType().get(i).getTypeNameAr());
                        item.setID(response.body().getResult().getLookUP().getCaseType().get(i).getID());
                        names.add(response.body().getResult().getLookUP().getCaseType().get(i).getTypeNameAr());
                        list.add(item);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, names);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            caseId = list.get(position).getID();

                            Log.d(TAG, "onItemSelected: " + id);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<LookUpDataModel> call, Throwable t) {

            }
        }, progress, null));


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                id = list.get(position).getID();
//
//                Log.d(TAG, "onItemSelected: " + id);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() != null) {
                    updateTheRequest(getArguments().getString("id"));
                } else
                    sendNewRequest();
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        SELECT_FILE);
            }
        });

        return view;
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        selectedImagePath = cursor.getString(column_index);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);
        substring = selectedImagePath.substring(selectedImagePath.lastIndexOf(".") + 1);

        imagepath = selectedImageUri.getPath();
        imageFile = new File(selectedImagePath);
        Bitmap finalImage = null;
        try {
            ExifInterface ei = new ExifInterface(selectedImagePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            Log.e("orientation", orientation + "");
            //finalImage = rotateImage(bm, 270);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    finalImage = rotateImage(bm, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    finalImage = rotateImage(bm, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    finalImage = rotateImage(bm, 270);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            }
        }

    }

    private void setUpPermissions() {
        String per[] = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(getActivity(), per, 1);
    }


    private void updateTheRequest(String updateId) {
        JSONObject jsonObject = new JSONObject();

        Log.d(TAG, "updateTheRequest: " + updateId);
        try {
            jsonObject.put("CaseRequestID", updateId);
            jsonObject.put("RType", id);
            jsonObject.put("RTitle", title.getText().toString());
            jsonObject.put("Description", description.getText().toString());
            jsonObject.put("LawyerID", user.getCustomerId());
            jsonObject.put("Isdeleted", false);
            jsonObject.put("IsActive", true);
            jsonObject.put("StatusID", user.getCustomerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).updateCaseRequest("UpdateCaseRequestData",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<SimpleModelResponse>() {
            @Override
            public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {

                Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SimpleModelResponse> call, Throwable t) {

            }
        }, progress, null));
    }

    private void sendNewRequest() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("Typeid", caseId);
            jsonObject.put("Title", title.getText().toString());
            jsonObject.put("Description", description.getText().toString());
            jsonObject.put("CustomerID", user.getCustomerId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MediaType mediaType = MediaType.parse("application/raw");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        ApiClient.getClient().create(ApiInterface.class).addCaseRequest("AddCaseRequest",
                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                getActivity(), new Callback<CaseRequestModel>() {
            @Override
            public void onResponse(Call<CaseRequestModel> call, Response<CaseRequestModel> response) {

                if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {
                    serviceId = String.valueOf(response.body().getResult().getCaseRequest().get(0).getCaseRequestID());
                    sendAttachment();
                }
//                Toast.makeText(getActivity(), response.body().getResult().getDetails(),
//
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CaseRequestModel> call, Throwable t) {

            }
        }, progress, null));
    }

    private void sendAttachment() {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("file", file.getAbsoluteFile());
//            jsonObject.put("userId", user.getId());
//            jsonObject.put("ServiceName", "Contract");
//            jsonObject.put("serviceID", serviceId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        AndroidNetworking.initialize(getActivity());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.upload("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addMultipartFile("file",imageFile)
                .addMultipartParameter("userId",user.getId())
            .addMultipartParameter("ServiceName","Contract")
                .addMultipartParameter("serviceID",serviceId)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("MethodName", "InsertAttachment")
                .addHeaders("x-user", user.getEmail())
                .addHeaders("x-pass", user.getPassword())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject result = response.getJSONObject("result");
                            if (result.getString("result").equals("OK")) {
                                Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), MainActivity.class));
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
//
//        final MediaType mediaType = MediaType.parse("multipart/form-data");
//        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
//        ApiClient.getClient().create(ApiInterface.class).addAttachment("InsertAttachment",
//                user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
//                getActivity(), new Callback<SimpleModelResponse>() {
//            @Override
//            public void onResponse(Call<SimpleModelResponse> call, Response<SimpleModelResponse> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(getActivity(), response.body().getResult().getDetails(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SimpleModelResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        }, progress, null
//        ));
    }
}
