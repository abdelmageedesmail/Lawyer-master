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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.app.LocaleShared;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.connection.ApiClient;
import al.ib.lawyer.connection.ApiInterface;
import al.ib.lawyer.connection.RetrofitCallBack;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.contract.DraftContractModel;
import al.ib.lawyer.model.simple.SimpleModelResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.FontsContract.Columns.RESULT_CODE_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DraftContactFragment extends Fragment {


    public static final String TAG = "DraftContactFragment";
    private static final int REQUEST_CODE = 55;
    private File file;
    private Bitmap bm;
    private Uri selectedImageUri;
    private String selectedImagePath,substring;
    private String imagepath;
    private File imageFile;
    int SELECT_FILE = 1;
    public DraftContactFragment() {
        // Required empty public constructor
    }

    private EditText etTitle, etDescription;
    private TextView tvBrowse, tvSend, tvFileTitle;
    private UserManager userManager;
    private User user;
    private ProgressBar progress;
    private String serviceId;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.draft_contract, container, false);
setUpPermissions();
        tvBrowse = view.findViewById(R.id.browse);
        tvSend = view.findViewById(R.id.send);
        etTitle = view.findViewById(R.id.title);
        tvFileTitle = view.findViewById(R.id.attachedFile);
        etDescription = view.findViewById(R.id.description);
        progress = view.findViewById(R.id.progress);

        userManager = new UserManager(getActivity());
        user = userManager.getUser();

        tvBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        SELECT_FILE);
            }
        });


        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleShared localeShared=new LocaleShared(getActivity());
                String mobile = localeShared.getKey("mobile");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Fname", "Noob");
                    jsonObject.put("Lname", "Noob");
                    jsonObject.put("Description", etDescription.getText().toString());
                    jsonObject.put("Email", user.getEmail());
                    jsonObject.put("UserId", user.getId());
                    jsonObject.put("Mobile", mobile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final MediaType mediaType = MediaType.parse("application/raw");
                RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));

                ApiClient.getClient().create(ApiInterface.class).draftContract("AddNewDraftContract",
                        user.getEmail(), user.getPassword(), body).enqueue(new RetrofitCallBack<>(
                        getActivity(), new Callback<DraftContractModel>() {
                    @Override
                    public void onResponse(Call<DraftContractModel> call, Response<DraftContractModel> response) {

                        if (response.isSuccessful() && response.body().getResult().getResult().equals("OK")) {
                            serviceId = String.valueOf(response.body().getResult().getDraftContract().get(0).getDraftContractID());
//                            if (file.isFile())
                                sendAttachment();
//                            else
//                                Toast.makeText(getActivity(), "Choose File ☹", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getActivity(), response.body().getResult().getDetails(),
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<DraftContractModel> call, Throwable t) {

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

    private void sendAttachment() {
        final ProgressDialog dialog =new ProgressDialog(getActivity());
        dialog.setMessage("Uploading");
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("file", imageFile);
            jsonObject.put("userId", user.getId());
            jsonObject.put("ServiceName", "Contract");
            jsonObject.put("serviceID", serviceId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.initialize(getActivity());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.upload("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addMultipartFile("file",imageFile)
                .addMultipartParameter("userId",user.getId())
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
                            dialog.dismiss();
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
                        dialog.dismiss();
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
//
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
}
