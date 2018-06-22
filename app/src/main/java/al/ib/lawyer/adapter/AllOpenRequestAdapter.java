package al.ib.lawyer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.interfaces.RequestClicked;
import al.ib.lawyer.model.allrequests.OpenRequestsItem;
import al.ib.lawyer.view.fragment.AddQuotation;
import al.ib.lawyer.view.fragment.QuotationFragment;

import static al.ib.lawyer.MainActivity.replaceFragment;

public class AllOpenRequestAdapter extends RecyclerView.Adapter<AllOpenRequestAdapter.ViewHolder> {

    private Context context;
    private List<OpenRequestsItem> list;
    private RequestClicked requestClicked;
    public static String serviceId;
    public AllOpenRequestAdapter(Context context, List<OpenRequestsItem> list, RequestClicked requestClicked) {
        this.list = list;
        this.context = context;
        this.requestClicked = requestClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.requests_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final OpenRequestsItem item = list.get(position);
        holder.title.setText(item.getRTitle());

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceId=String.valueOf(item.getId());
                requestClicked.openRequest(String.valueOf(item.getId()));
            }
        });

        holder.attachQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuotationFragment addQuotation = new QuotationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(item.getId()));
                bundle.putString("picId", String.valueOf(item.getPictureId()));
                bundle.putString("statId", item.getStatusId());
                bundle.putString("userId", item.getLUserId());
                addQuotation.setArguments(bundle);
                replaceFragment(addQuotation, "QuotationFragment", context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, viewDetails, attachQuotation;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            attachQuotation = itemView.findViewById(R.id.attachQuotation);
        }
    }
}
