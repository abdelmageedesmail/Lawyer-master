package al.ib.lawyer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.model.lawyerrequests.OpenRequestsItem;
import al.ib.lawyer.view.fragment.ConsultationRequest;

import static al.ib.lawyer.MainActivity.replaceFragment;


public class MyOpenRequestCustomerAdapter extends RecyclerView.Adapter<MyOpenRequestCustomerAdapter.ViewHolder> {

    private Context context;
    private List<OpenRequestsItem> list;
    private CustomerOpenRequests customerOpenRequests;

    public MyOpenRequestCustomerAdapter(Context context, List<OpenRequestsItem> list, CustomerOpenRequests customerOpenRequests) {
        this.list = list;
        this.context = context;
        this.customerOpenRequests =customerOpenRequests;
    }

    public interface CustomerOpenRequests{
        void deleteClicked(String id);
    }
    @NonNull
    @Override
    public MyOpenRequestCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOpenRequestCustomerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.open_requests_customer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOpenRequestCustomerAdapter.ViewHolder holder, int position) {

        final OpenRequestsItem item = list.get(position);
        holder.title.setText(item.getRTitle());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(item.getId()));
                ConsultationRequest fragment = new ConsultationRequest();
                fragment.setArguments(bundle);
                replaceFragment(fragment,  "ConsultationRequest", context);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerOpenRequests.deleteClicked(String.valueOf(item.getId()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, edit, delete, qutoation;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            qutoation = itemView.findViewById(R.id.quotation);
        }
    }
}
