package al.ib.lawyer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.interfaces.RequestClicked;
import al.ib.lawyer.model.history.HistoryRequestsItem;
import al.ib.lawyer.view.fragment.LawyerProfileFragment;

import static al.ib.lawyer.MainActivity.replaceFragment;

public class RequestHistoryAdapter extends RecyclerView.Adapter<RequestHistoryAdapter.ViewHolder> {

    private Context context;
    private List<HistoryRequestsItem> list;

    public RequestHistoryAdapter(Context context, List<HistoryRequestsItem> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public RequestHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestHistoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHistoryAdapter.ViewHolder holder, int position) {

        final HistoryRequestsItem item = list.get(position);
        holder.lawyerName.setText(item.getRTitle());
        holder.date.setText(item.getEntryDate());

        holder.viewLaywer.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lawyerName, date, viewLaywer;

        public ViewHolder(View itemView) {
            super(itemView);

            lawyerName = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            viewLaywer = itemView.findViewById(R.id.viewLawyer);
        }
    }
}
