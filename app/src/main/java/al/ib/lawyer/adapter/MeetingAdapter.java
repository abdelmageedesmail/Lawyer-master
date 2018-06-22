package al.ib.lawyer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import al.ib.lawyer.R;
import al.ib.lawyer.model.lawyermeeting.LawyerMeetingItem;
import al.ib.lawyer.view.fragment.BookMeetFragment;

import static al.ib.lawyer.MainActivity.addFragment;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private Context context;
    private List<LawyerMeetingItem> list;

    public MeetingAdapter(Context context, List<LawyerMeetingItem> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MeetingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeetingAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.ViewHolder holder, int position) {

        LawyerMeetingItem item = list.get(position);

        holder.name.setText(item.getLastNameEn());
        holder.date.setText(item.getLDate());
        holder.time.setText(item.getMtime());

        holder.bookMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(new BookMeetFragment(), BookMeetFragment.TAG, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, bookMeet, date, time;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            bookMeet = itemView.findViewById(R.id.book);
        }
    }
}