package al.ib.lawyer.adapter;

import android.content.Context;
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

import al.ib.lawyer.R;
import al.ib.lawyer.model.QuotationModel;

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.ViewHolder>{

    private Context context;
    private List<QuotationModel> list = new ArrayList<>();

    public QuotationAdapter(Context context, List<QuotationModel> list){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public QuotationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuotationAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quotations_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuotationAdapter.ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getName());
        holder.lawyerDesc.setText(list.get(position).getDescription());
        holder.lawyerDetails.setText(list.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,lawyerDesc,lawyerDetails;
        ImageView lawyerPic;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            lawyerDesc=itemView.findViewById(R.id.lawyerDesc);
            lawyerDetails=itemView.findViewById(R.id.lawyerDetails);
            lawyerPic=itemView.findViewById(R.id.lawyerPic);
        }
    }
}