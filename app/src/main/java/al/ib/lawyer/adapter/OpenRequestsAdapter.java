package al.ib.lawyer.adapter;

import android.content.Context;
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

public class OpenRequestsAdapter extends RecyclerView.Adapter<OpenRequestsAdapter.ViewHolder>{

    private Context context;
    private List<OpenRequestsItem> list = new ArrayList<>();

    public OpenRequestsAdapter(Context context, List<OpenRequestsItem> list){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public OpenRequestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OpenRequestsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.open_requests_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OpenRequestsAdapter.ViewHolder holder, int position) {
        OpenRequestsItem model = list.get(position);

        holder.requestDescription.setText(model.getRDescription());
        holder.requestDate.setText(model.getEntryDate());
        holder.requestName.setText(model.getRTitle());


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
