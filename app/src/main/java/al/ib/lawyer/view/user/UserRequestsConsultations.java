package al.ib.lawyer.view.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import al.ib.lawyer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserRequestsConsultations extends Fragment {


    public UserRequestsConsultations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_requests_consultations, container, false);

        return view;
    }

}
