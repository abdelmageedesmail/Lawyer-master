package al.ib.lawyer.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import al.ib.lawyer.R;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.view.SelectLanguageActivity;
import al.ib.lawyer.view.fragment.AboutUsFragment;
import al.ib.lawyer.view.fragment.ClaimsAndComplainsFragment;
import al.ib.lawyer.view.fragment.ConsultationRequest;
import al.ib.lawyer.view.fragment.ConsultationRequestsFragment;
import al.ib.lawyer.view.fragment.ContactUsFragment;
import al.ib.lawyer.view.fragment.DraftContactFragment;
import al.ib.lawyer.view.fragment.LawyerProfileFragment;
import al.ib.lawyer.view.fragment.MeetingsFragment;
import al.ib.lawyer.view.fragment.PrivacyConditionFragment;
import al.ib.lawyer.view.fragment.QuotationFragment;
import al.ib.lawyer.view.fragment.RequestMeetingFragment;
import al.ib.lawyer.view.user.UserProfileFragment;

import static al.ib.lawyer.MainActivity.replaceFragment;
import static al.ib.lawyer.app.UserManager.USER;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private UserManager userManager;

    private int[] drawables;
    private static final String TAG = "ExpandableListAdapter";

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        userManager = new UserManager(context);
        if (userManager.getUser().isLawyer()){
            drawables = new int[]{R.drawable.sidemenu_icon1, R.drawable.sidemenu_icon2, R.drawable.sidemenu_icon3,
                    R.drawable.sidemenu_icon4, R.drawable.sidemenu_icon5, R.drawable.sidemenu_icon6, R.drawable.sidemenu_icon7, R.drawable.sidemenu_icon7,
                    R.drawable.membership,R.drawable.sidemenu_icon8};
        }else {
            drawables = new int[]{R.drawable.sidemenu_icon1, R.drawable.sidemenu_icon2, R.drawable.sidemenu_icon3,
                    R.drawable.sidemenu_icon4, R.drawable.sidemenu_icon5, R.drawable.sidemenu_icon6, R.drawable.sidemenu_icon7, R.drawable.ic_warning,
                    R.drawable.sidemenu_icon8};
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);


        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                != null ? this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        RelativeLayout layout = convertView.findViewById(R.id.layout);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        ImageView arrow = convertView.findViewById(R.id.expandArrow);
        if (groupPosition == 1)
            arrow.setVisibility(View.VISIBLE);
        else arrow.setVisibility(View.GONE);
        imageView.setImageDrawable(_context.getDrawable(drawables[groupPosition]));
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
