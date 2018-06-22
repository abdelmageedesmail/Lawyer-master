package al.ib.lawyer;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.EditText;

import java.util.Locale;

public class EditTextCustomLanguage extends android.support.v7.widget.AppCompatEditText {


    public EditTextCustomLanguage(Context context) {
        super(context);

        init(context);
    }


    public EditTextCustomLanguage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.EditTextCustomLanguage);

        String attr = typedArray.getString(R.styleable.EditTextCustomLanguage_custom_language);
        // Make a public mutator so that others can set this attribute programatically
        setLanguage(attr);
        // Remember to recycle the TypedArray (saved memory)
        typedArray.recycle();
    }

    public EditTextCustomLanguage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setLanguage(String lang){
        if (lang.equals("ar"))
            setLang("ar");
        else setLang("en");
    }

    private void setLang(String lang){
        Locale locale = new Locale(lang);
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale);
        } else{
            configuration.locale=locale;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            this.getContext().createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration,displayMetrics);
        }
    }
}
