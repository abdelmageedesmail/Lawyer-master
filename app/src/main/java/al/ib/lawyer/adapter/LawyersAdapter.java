package al.ib.lawyer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.MainActivity;
import al.ib.lawyer.R;
import al.ib.lawyer.model.lawyers.LawyerItem;
import al.ib.lawyer.view.fragment.BookMeetFragment;
import al.ib.lawyer.view.fragment.ConsultationRequest;
import al.ib.lawyer.view.fragment.LawyerProfileFragment;

import static al.ib.lawyer.MainActivity.replaceFragment;

public class LawyersAdapter extends RecyclerView.Adapter<LawyersAdapter.ViewHolder> {

    private Context context;
    private List<LawyerItem> list = new ArrayList<>();

    public LawyersAdapter(Context context, List<LawyerItem> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LawyersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LawyersAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lawyer_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LawyersAdapter.ViewHolder holder, int position) {

        final LawyerItem item = list.get(position);
        holder.lawyerName.setText(item.getFullNameEn());
        holder.desc.setText(item.getDescription());
        holder.fee.setText(item.getConsultationFee());
        holder.time.setText(item.getOfficeTimingAr());
        Glide.with(context).load(item.getPictureURL()).into(holder.pic);
        holder.bookMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "customer");
                bundle.putString("id", String.valueOf(item.getLawyerID()));
                BookMeetFragment fragment = new BookMeetFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment, "BookMeetFragment", context);

            }
        });
        holder.lawyerProfile.setOnClickListener(new View.OnClickListener() {
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

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ConsultationRequest(), ConsultationRequest.TAG, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lawyerName, lawyerProfile, bookMeet, desc, fee, time, request;
        private ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);

            lawyerName = itemView.findViewById(R.id.name);
            lawyerProfile = itemView.findViewById(R.id.profile);
            bookMeet = itemView.findViewById(R.id.lawyerBook);
            desc = itemView.findViewById(R.id.desc);
            fee = itemView.findViewById(R.id.fee);
            time = itemView.findViewById(R.id.time);
            request = itemView.findViewById(R.id.request);
            pic = itemView.findViewById(R.id.lawyerPic);
        }
    }
}
