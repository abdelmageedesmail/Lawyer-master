package al.ib.lawyer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.model.lawyerrequests.OpenRequestsItem;
import al.ib.lawyer.model.privaterequest.PrivateRequestsItem;
import al.ib.lawyer.view.fragment.AddQuotation;

import static al.ib.lawyer.MainActivity.replaceFragment;

public class PrivateRequestsAdapter extends RecyclerView.Adapter<PrivateRequestsAdapter.ViewHolder>{

    private Context context;
    private List<PrivateRequestsItem> list = new ArrayList<>();

    public PrivateRequestsAdapter(Context context, List<PrivateRequestsItem> list){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public PrivateRequestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrivateRequestsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.open_requests_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrivateRequestsAdapter.ViewHolder holder, int position) {
        final PrivateRequestsItem model = list.get(position);

        holder.requestDescription.setText(model.getRDescription());
        holder.requestDate.setText(model.getEntryDate());
        holder.requestName.setText(model.getRTitle());
        holder.attachQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddQuotation addQuotation = new AddQuotation();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(model.getId()));
                bundle.putString("picId", String.valueOf(model.getPictureId()));
                bundle.putString("statId", model.getStatusId());
                bundle.putString("userId", model.getLUserId());
                addQuotation.setArguments(bundle);
                replaceFragment(addQuotation, "AddQutaiton", context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView requestName, requestDate, requestDescription, viewDetails, attachQuotation;
        public ViewHolder(View itemView) {
            super(itemView);

            requestName = itemView.findViewById(R.id.requestName);
            requestDate = itemView.findViewById(R.id.date);
            requestDescription = itemView.findViewById(R.id.desc);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            attachQuotation = itemView.findViewById(R.id.attachQuotation);

        }
    }
}
