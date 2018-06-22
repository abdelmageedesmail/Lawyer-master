package al.ib.lawyer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.model.User;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingItem;
import al.ib.lawyer.view.fragment.BookMeetFragment;
import al.ib.lawyer.view.fragment.LawyerProfileFragment;

import static al.ib.lawyer.MainActivity.replaceFragment;

public class RequestMeetingFragmentAdapter extends RecyclerView.Adapter<RequestMeetingFragmentAdapter.ViewHolder> {

    private Context context;
    private List<LawyerMeetingItem> list = new ArrayList<>();
    private UserManager userManager;
    private User user;

    private static final String TAG = "RequestMeetingFragmentA";

    public RequestMeetingFragmentAdapter(Context context, List<LawyerMeetingItem> list) {
        this.list = list;
        this.context = context;
        userManager = new UserManager(context);
        user = userManager.getUser();
    }

    @NonNull
    @Override
    public RequestMeetingFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (user.isLawyer())
            return new RequestMeetingFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.meeting_requests_row, parent, false));
        else {
            return new RequestMeetingFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.meeting_row, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RequestMeetingFragmentAdapter.ViewHolder holder, final int position) {

        final LawyerMeetingItem item = list.get(position);


        if (user.isLawyer())
            holder.requestDesc.setText(item.getDescription() == null ? "--" : item.getDescription());

        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (!user.isLawyer()) {
            holder.time.setText(item.getMtime());
            holder.bookMeet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "customer");
                    bundle.putBoolean("update", true);
                    bundle.putString("date", item.getMdate());
                    bundle.putString("time", item.getMtime());
                    bundle.putString("desc", item.getDescription());
                    bundle.putString("desc", item.getDescription());
                    bundle.putString("meeting_id", String.valueOf(item.getID()));
                    bundle.putString("id", String.valueOf(item.getLawyerID()));
                    BookMeetFragment fragment = new BookMeetFragment();
                    fragment.setArguments(bundle);
                    replaceFragment(fragment, "BookMeetFragment", context);

                }
            });
            holder.profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putString("type", "customer");
                    bundle.putString("id", String.valueOf(item.getLawyerID()));
                    LawyerProfileFragment fragment = new LawyerProfileFragment();
                    fragment.setArguments(bundle);
                    replaceFragment(fragment, "LawyerProfileFragment", context);
                }
            });
        }


        if (item.getMdate() != null) {
            try {

                Date date = inputFormat.parse(item.getMdate());
                holder.requestDate.setText(outputFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        holder.requestName.setText(""+item.getLawyerNameEn());
        holder.requestDate.setText(""+item.getEntryDate());
        holder.time.setText(""+item.getMtime());
        holder.requestDesc.setText(""+item.getDescription());
        if (userManager.getUser().isLawyer()){
            holder.rescheduled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reSchduleMetting(position);
                }
            });
        }

    }

    private void reSchduleMetting(int position){
        JSONObject object=new JSONObject();
        try {
            object.put("MeetingID",""+list.get(position).getID());
            object.put("mDate",list.get(position).getEntryDate());
            object.put("mTime",list.get(position).getMtime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(context.getString(R.string.uploading));
        progressDialog.show();
        AndroidNetworking.initialize(context);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        AndroidNetworking.post("http://mylawyerws.fngroups.com/frmLawyerService.aspx")
                .addHeaders("MethodName","RescheduleMeeting")
                .addHeaders("x-user",user.getEmail())
                .addHeaders("x-pass",user.getPassword())
                .addJSONObjectBody(object)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            String status = response.getString("result");
                            if (status.equals("OK")){
                                Toast.makeText(context, context.getString(R.string.YourMeetingIsRescheduled), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                    }
                });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView requestName, requestDate, requestDesc, bookMeet, profile, time;
        Button rescheduled;
        public ViewHolder(View itemView) {
            super(itemView);

            requestName = itemView.findViewById(R.id.name);
            requestDate = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            requestDesc = itemView.findViewById(R.id.desc);
            bookMeet = itemView.findViewById(R.id.book);
            profile = itemView.findViewById(R.id.lawyerProfile);
            rescheduled=itemView.findViewById(R.id.rescheduled);
        }
    }
}