package al.ib.lawyer.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppManager {

    private Context context;

    // SharedPreference
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    private String SHARED_PREF_NAME = "LAWYER_APP_MANAGER";
    private int MODE_PRIVATE = 0;

    // Keys
    private static final String LANGUAGE = "language";
    private static final String FIRST_RUN = "first_run";

    public AppManager(Context context){
        this.context = context;
        editor = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit();
        pref = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    public void setLanguage(String language){
        editor.putString(LANGUAGE, language).apply();
    }

    public String getLanguage(){
        return pref.getString(LANGUAGE, null);
    }

    public void setFirstRun(boolean isFirstRun){
        editor.putBoolean(FIRST_RUN, isFirstRun).apply();
    }

    public boolean isFirstRun(){
        return pref.getBoolean(FIRST_RUN, false);
    }

}
