package al.ib.lawyer.view.fragment;


import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ProgressBar;
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

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.qutation.QutationModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuotation extends Fragment {


    private static final int REQUEST_CODE = 50;
    private Bitmap bm;
    private Uri selectedImageUri;
    private String selectedImagePath,substring;
    private String imagepath;
    private File imageFile;
    int SELECT_FILE = 1;

    public AddQuotation() {
        // Required empty public constructor
    }

    private TextView fileName, browse, send;
    private EditText description;
    private UserManager userManager;
    private User user;
    private ProgressBar progress;
    String qutationId;
    private File file;
    private static final String TAG = "AddQuotation";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setUpPermissions();
        View view = inflater.inflate(R.layout.fragment_add_quotation, container, false);

        fileName = view.findViewById(R.id.attachedFile);
        browse = view.findViewById(R.id.browse);
        send = view.findViewById(R.id.send);
        description = view.findViewById(R.id.description);
        progress = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();
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
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("CaseRequestID", getArguments().getString("id"));
                    jsonObject.put("LawyerID", user.getLawyerId());
                    jsonObject.put("PictureId", getArguments().getString("picId"));
                    jsonObject.put("Description", description.getText().toString());
                    jsonObject.put("StatusId", getArguments().getString("statId"));
                    jsonObject.put("LUserId", getArguments().getString("userId"));
                    Log.e("json",""+jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final MediaType mediaType = MediaType.parse("application/raw");
                RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
                ApiClient.getClient().create(ApiInterface.class).addQutation("AddNewQuotation",
                         user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                        getActivity(), new Callback<QutationModel>() {
                    @Override
                    public void onResponse(Call<QutationModel> call, Response<QutationModel> response) {

                        if (response.isSuccessful()){
                            qutationId = String.valueOf(response.body().getResult().getQuotation().get(0).getQuotationID());
                            sendAttachment(qutationId);
                        }
                    }

                    @Override
                    public void onFailure(Call<QutationModel> call, Throwable t) {

                    }
                }, progress, null
                ));
            }
        });
        return view;
    }


    private void setUpPermissions() {
        String per[] = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(getActivity(), per, 1);
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

    private void sendAttachment(String id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("file", file.getAbsoluteFile());
            jsonObject.put("userId", user.getId());
            jsonObject.put("ServiceName", "quotation");
            jsonObject.put("serviceID", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.initialize(getActivity());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.upload("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addMultipartFile("file",imageFile)
                .addMultipartParameter("userId",user.getId())
                .addMultipartParameter("ServiceName","quotation")
                .addMultipartParameter("serviceID",qutationId)
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
