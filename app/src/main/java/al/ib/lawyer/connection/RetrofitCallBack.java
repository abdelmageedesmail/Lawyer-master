package al.ib.lawyer.connection;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitCallBack<T> implements Callback<T> {
    @SuppressWarnings("unused")
    private static final String TAG = "RetrofitCallback";
    private Context mContext;
    private final Callback<T> mCallback;
    private ProgressBar mProgress;
    private View view;


    public RetrofitCallBack(Context context, Callback<T> callback, ProgressBar mProgress, View view) {
        mContext = context;
        this.mCallback = callback;
        this.mProgress = mProgress;
        this.view = view;

        if (mProgress != null)
            mProgress.setVisibility(View.VISIBLE);

        if (view != null)
            view.setVisibility(View.GONE);

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
// Do application relavent custom operation like manupulating reponse etc.

        Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
        mCallback.onResponse(call, response);

        if (mProgress != null)
            mProgress.setVisibility(View.GONE);

        if (view != null)
            view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
// Handle error etc.
        // NetworkHelper.onFailure(t, (Activity) mContext);
        mCallback.onFailure(call, t);

        Log.d(TAG, "onFailure: " + t.getMessage());
        if (mProgress != null)
            mProgress.setVisibility(View.GONE);

        if (view != null)
            view.setVisibility(View.VISIBLE);
    }
}