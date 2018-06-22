package al.ib.lawyer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.interfaces.RequestClicked;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.allrequests.OpenRequestsItem;
import al.ib.lawyer.model.requestDetails.RequestDetailsItem;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;


public class AttachMentAdapter extends RecyclerView.Adapter<AttachMentAdapter.ViewHolder> {

    private final User user;
    private Context context;
    private List<RequestDetailsItem> list;
    private static final String TAG = "AttachMentAdapter";
    UserManager userManager;

    public AttachMentAdapter(Context context, List<RequestDetailsItem> list) {
        this.list = list;
        this.context = context;
        userManager = new UserManager(context);
        user = userManager.getUser();

    }

    @NonNull
    @Override
    public AttachMentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttachMentAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attchment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AttachMentAdapter.ViewHolder holder, final int position) {
        RequestDetailsItem item = list.get(position);
        Log.e("attachment", "" + list.get(position).getDownloadUrl());
        holder.attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (list.get(position).getDownloadUrl()!=null){
//                    Uri intentUri = Uri.parse(list.get(position).getDownloadUrl());
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(intentUri);
//                    context.startActivity(intent);
//                }else{
//                    Toast.makeText(context, context.getString(R.string.errorAttachment), Toast.LENGTH_SHORT).show();
//                }
                getAttachemnt(position);

            }
        });
    }

    private void getAttachemnt(final int position) {
        JSONObject object = new JSONObject();
        try {
            object.put("FileID", list.get(position).getDownloadUrl());
            object.put("ServiceName", "CaseRequest");
            object.put("ServiceID", AllOpenRequestAdapter.serviceId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.initialize(context);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addJSONObjectBody(object)
                .addHeaders("MethodName", "GetAttachment")
                .addHeaders("x-user", user.getEmail())
                .addHeaders("x-pass", user.getPassword())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response", "" + response);
                        try {
                            JSONObject result = response.getJSONObject("result");
                            String result1 = result.getString("result");
                            if (result1.equals("OK")) {
                                JSONArray attachedFiled = result.getJSONArray("AttachedFiles");
                                JSONObject jsonObject = attachedFiled.getJSONObject(0);
                                Uri intentUri = Uri.parse(jsonObject.getString("FileURL"));
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(intentUri);
                                context.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("error", "" + anError.getMessage());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView attachment;

        public ViewHolder(View itemView) {
            super(itemView);

            attachment = itemView.findViewById(R.id.attachedFile);
        }
    }
}
