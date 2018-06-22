package al.ib.lawyer.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class ChangeLanguage {

    @SuppressWarnings("deprecation")
    public static void updateLanguage(Context ctx, String lang, boolean hasToStart) {


        Locale myLocale = new Locale(lang);
        Resources res = ctx.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1){
        //conf.setLocale(myLocale);
        //  createConfigurationContext(conf);
        //}else {
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //}


//        Locale locale = new Locale(lang);
//        Resources resources = ctx.getResources();
//        Configuration configuration = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
//            configuration.setLocale(locale);
//        } else{
//            configuration.locale=locale;
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//            ctx.getApplicationContext().createConfigurationContext(configuration);
//        } else {
//            resources.updateConfiguration(configuration,displayMetrics);
//        }


        if (hasToStart) {
            Intent i = ctx.getPackageManager()
                    .getLaunchIntentForPackage(ctx.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ctx.startActivity(i);
        }

    }
}
