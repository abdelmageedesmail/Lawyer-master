package al.ib.lawyer;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import al.ib.lawyer.adapter.ExpandableListAdapter;
import al.ib.lawyer.app.ChangeLanguage;
import al.ib.lawyer.app.UserManager;
import al.ib.lawyer.model.User;
import al.ib.lawyer.view.SelectLanguageActivity;
import al.ib.lawyer.view.fragment.AboutUsFragment;
import al.ib.lawyer.view.fragment.ClaimsAndComplainsFragment;
import al.ib.lawyer.view.fragment.ConsultationRequest;
import al.ib.lawyer.view.fragment.ConsultationRequestsFragment;
import al.ib.lawyer.view.fragment.ContactUsFragment;
import al.ib.lawyer.view.fragment.DraftContactFragment;
import al.ib.lawyer.view.fragment.LawyerFragment;
import al.ib.lawyer.view.fragment.LawyerMemberShip;
import al.ib.lawyer.view.fragment.LawyerProfileFragment;
import al.ib.lawyer.view.fragment.MeetingsFragment;
import al.ib.lawyer.view.fragment.OpenRequestsFragment;
import al.ib.lawyer.view.fragment.PrivacyConditionFragment;
import al.ib.lawyer.view.fragment.PrivateRequestFragment;
import al.ib.lawyer.view.fragment.QuotationFragment;
import al.ib.lawyer.view.fragment.RequestMeetingFragment;
import al.ib.lawyer.view.user.UserProfileFragment;

import static al.ib.lawyer.app.UserManager.USER;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    static DrawerLayout drawer;
    private ImageView profile, requests, meetings, lawyers;
    private UserManager userManager;

    private static final String TAG = "MainActivity";
    private User user;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences shaerd = getSharedPreferences("userData", MODE_PRIVATE);
        String language = shaerd.getString("language", "null");
        if (language.equals("ar")) {
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else if (language.equals("en")) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }

        profile = findViewById(R.id.profile);
        requests = findViewById(R.id.requests);
        meetings = findViewById(R.id.meeting);
        lawyers = findViewById(R.id.lawyers);

        userManager = new UserManager(this);
        profile.setOnClickListener(this);
        meetings.setOnClickListener(this);
        requests.setOnClickListener(this);
        lawyers.setOnClickListener(this);

        if (!userManager.getUser().isLawyer()){
            profile.setImageResource(R.drawable.footmenu);
            requests.setImageResource(R.drawable.footmenu6);
            meetings.setImageResource(R.drawable.footmenu7);
            lawyers.setImageResource(R.drawable.footmenu8);
        }
        // get the listview
        expListView = findViewById(R.id.listView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);


        userManager = new UserManager(this);
        user = userManager.getUser();
        Log.e("data", "" + user.getPassword() + "..." + user.getEmail());
        // setting list adapter
        expListView.setAdapter(listAdapter);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getIntent().hasExtra("register"))
            replaceFragment(new LawyerMemberShip(), "LawyerMemberShip", this);
        else
            profileFragment();

        Log.d(TAG, "onCreate: " + userManager.getUser().getPassword());


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (!userManager.getUser().isLawyer())
                    customerChild(childPosition);
                else lawyerChild(childPosition);

                return true;
            }
        });
        groupClicks();
    }

    private void lawyerChild(int childPosition) {
        switch (childPosition) {

            case 0:
                replaceFragment(new ConsultationRequestsFragment(), ConsultationRequestsFragment.TAG, MainActivity.this);
                break;
            case 1:
                replaceFragment(new DraftContactFragment(), DraftContactFragment.TAG, MainActivity.this);
                break;
        }
    }

    private void customerChild(int childPosition) {
        switch (childPosition) {

            case 0:
                replaceFragment(new ConsultationRequest(), ConsultationRequest.TAG, MainActivity.this);
                break;
            case 1:
                replaceFragment(new ConsultationRequestsFragment(), ConsultationRequestsFragment.TAG, MainActivity.this);
                break;
            case 2:
                replaceFragment(new DraftContactFragment(), DraftContactFragment.TAG, MainActivity.this);
                break;
        }
    }

    private void groupClicks() {
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                if (userManager.getUser().isLawyer())
                    lawyerSwitcher(groupPosition);
                else
                    customerSwitcher(groupPosition);
                return true;
            }
        });
    }

    private void customerSwitcher(int groupPosition) {
        switch (groupPosition) {

            case 0:
                if (userManager.getUserType().equals(USER))
                    replaceFragment(new UserProfileFragment(), UserProfileFragment.TAG, MainActivity.this);
                else
                    replaceFragment(new LawyerProfileFragment(), LawyerProfileFragment.TAG, MainActivity.this);
                break;
            case 1:
                if (expListView.isGroupExpanded(1))
                    expListView.collapseGroup(1);
                else expListView.expandGroup(1);
                break;
            case 2:
                if (userManager.getUser().equals(USER))
                    replaceFragment(new MeetingsFragment(), MeetingsFragment.TAG, MainActivity.this);
                else
                    replaceFragment(new RequestMeetingFragment(), RequestMeetingFragment.TAG, MainActivity.this);
                break;
            case 3:
                replaceFragment(new QuotationFragment(), QuotationFragment.TAG, MainActivity.this);
                break;
            case 4:
                replaceFragment(new AboutUsFragment(), AboutUsFragment.TAG, MainActivity.this);
                break;
            case 5:
                replaceFragment(new PrivacyConditionFragment(), PrivacyConditionFragment.TAG, MainActivity.this);
                break;
            case 6:
                replaceFragment(new ClaimsAndComplainsFragment(), ClaimsAndComplainsFragment.TAG, MainActivity.this);
                break;
            case 7:
                replaceFragment(new ContactUsFragment(), ContactUsFragment.TAG, MainActivity.this);
                break;
            case 8:
                userManager.logOut();
                Intent in = new Intent(MainActivity.this, SelectLanguageActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(in);
                break;
        }
    }

    private void lawyerSwitcher(int groupPosition) {
        switch (groupPosition) {

            case 0:
                if (userManager.getUserType().equals(USER))
                    replaceFragment(new UserProfileFragment(), UserProfileFragment.TAG, MainActivity.this);
                else
                    replaceFragment(new LawyerProfileFragment(), LawyerProfileFragment.TAG, MainActivity.this);
                break;
            case 1:
                if (expListView.isGroupExpanded(1))
                    expListView.collapseGroup(1);
                else expListView.expandGroup(1);
                break;
            case 2:
                if (userManager.getUser().equals(USER))
                    replaceFragment(new MeetingsFragment(), MeetingsFragment.TAG, MainActivity.this);
                else
                    replaceFragment(new RequestMeetingFragment(), RequestMeetingFragment.TAG, MainActivity.this);
                break;
            case 3:
                replaceFragment(new QuotationFragment(), QuotationFragment.TAG, MainActivity.this);
                break;
            case 4:
                replaceFragment(new AboutUsFragment(), AboutUsFragment.TAG, MainActivity.this);
                break;
            case 5:
                replaceFragment(new PrivacyConditionFragment(), PrivacyConditionFragment.TAG, MainActivity.this);
                break;
            case 6:
                replaceFragment(new ClaimsAndComplainsFragment(), ClaimsAndComplainsFragment.TAG, MainActivity.this);
                break;
            case 7:
                replaceFragment(new ContactUsFragment(), ContactUsFragment.TAG, MainActivity.this);
                break;
            case 9:
                userManager.logOut();
                Intent in = new Intent(MainActivity.this, SelectLanguageActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.this.startActivity(in);
                break;
            case 8:
                replaceFragment(new LawyerMemberShip(), "LawyerMemberShip", MainActivity.this);
                break;


        }
    }

    public static void replaceFragment(Fragment fragment, String tag, Context context) {

        FragmentActivity activity = (FragmentActivity) context;
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
    }

    public static void addFragment(Fragment fragment, String tag, Context context) {

        FragmentActivity activity = (FragmentActivity) context;
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Dialog dialog = initLanguageDialog();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.lang) {
            dialog.show();
        } else if (id == R.id.user) {

            profileFragment();

        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Dialog initLanguageDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.language_pop_up);
        TextView arabic = dialog.findViewById(R.id.arabic);
        TextView english = dialog.findViewById(R.id.english);
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                SharedPreferences sh = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor edit = sh.edit();
                edit.putString("language", "ar").apply();
                Locale locale = new Locale("ar");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                SharedPreferences sh = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor edit = sh.edit();
                edit.putString("language", "en").apply();
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            }
        });
        return dialog;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
            listDataHeader.add("الصفحه الشخصيه");
            listDataHeader.add("طلباتي");
            listDataHeader.add("اجتماعاتي");
            listDataHeader.add("اقوال المحامي");
            listDataHeader.add("عن التطبيق");
            listDataHeader.add("سياسة الخصوصيه");
            listDataHeader.add("الشكاوى");
            listDataHeader.add("اتصل بنا");
            if (userManager.getUser().isLawyer()) {
                listDataHeader.add("العضويات");
            }
            listDataHeader.add("تسجيل خروج");

        }else {
            listDataHeader.add("Profile");
            listDataHeader.add("My Requests");
            listDataHeader.add("My Meetings");
            listDataHeader.add("Lawyer Quotations");
            listDataHeader.add("About Us");
            listDataHeader.add("Privacy Conditions");
            listDataHeader.add("Claims & Complains");
            listDataHeader.add("Contact Us");
            if (userManager.getUser().isLawyer()) {
                listDataHeader.add("Member Ship");
            }
            listDataHeader.add("Log Out");

        }


        List<String> requests = new ArrayList<String>();
        if (!userManager.getUser().isLawyer())
            requests.add("New Request");
        requests.add("All Consultations");
        requests.add("Contracts");


        listDataChild.put(listDataHeader.get(0), null); // Header, Child data
        listDataChild.put(listDataHeader.get(1), requests);
        listDataChild.put(listDataHeader.get(2), null);
        listDataChild.put(listDataHeader.get(3), null);
        listDataChild.put(listDataHeader.get(4), null);
        listDataChild.put(listDataHeader.get(5), null);
        listDataChild.put(listDataHeader.get(6), null);
        listDataChild.put(listDataHeader.get(7), null);
        listDataChild.put(listDataHeader.get(8), null);
        if (userManager.getUser().isLawyer())
            listDataChild.put(listDataHeader.get(9), null);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.profile:
                profileFragment();
                break;

            case R.id.requests:

                //if (userManager.getUserType().equals(USER))
                //  replaceFragment(new OpenRequestsFragment(), OpenRequestsFragment.TAG, MainActivity.this);
                //else
                if (userManager.getUser().isLawyer()){
                    replaceFragment(new OpenRequestsFragment(), OpenRequestsFragment.TAG, MainActivity.this);
                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu2sel));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu3));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu4));
                }else {
                    replaceFragment(new OpenRequestsFragment(), OpenRequestsFragment.TAG, MainActivity.this);

                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu6sel));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu3));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu8));
                }
                break;
            case R.id.meeting:
                //if (userManager.getUser().equals(USER))
                //  replaceFragment(new MeetingsFragment(), MeetingsFragment.TAG, MainActivity.this);
                //else
                if (userManager.getUser().isLawyer()){
                    replaceFragment(new RequestMeetingFragment(), RequestMeetingFragment.TAG, MainActivity.this);
                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu2));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu3sel));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu4));


                }else {
                    replaceFragment(new RequestMeetingFragment(), RequestMeetingFragment.TAG, MainActivity.this);
                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu6));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu7sel));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu8));
                }

                break;

            case R.id.lawyers:
                if (userManager.getUser().isLawyer()){
                    replaceFragment(new PrivateRequestFragment(), PrivateRequestFragment.TAG, MainActivity.this);
                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu2));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu3));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu4sel));
                }
                else{
                    replaceFragment(new LawyerFragment(), LawyerFragment.TAG, MainActivity.this);
                    profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu));
                    requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu6));
                    meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu7));
                    lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu8sel));
                }
                break;
        }
    }

    private void profileFragment() {
        if (userManager.getUserType().equals(USER)){
            replaceFragment(new UserProfileFragment(), UserProfileFragment.TAG, MainActivity.this);
            profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu1sel));
            requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu6));
            meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu7));
            lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu8));

        }
        else{
            replaceFragment(new LawyerProfileFragment(), LawyerProfileFragment.TAG, MainActivity.this);

            profile.setImageDrawable(getResources().getDrawable(R.drawable.footmenu1sel));
            requests.setImageDrawable(getResources().getDrawable(R.drawable.footmenu2));
            meetings.setImageDrawable(getResources().getDrawable(R.drawable.footmenu3));
            lawyers.setImageDrawable(getResources().getDrawable(R.drawable.footmenu4));

        }

    }


}
