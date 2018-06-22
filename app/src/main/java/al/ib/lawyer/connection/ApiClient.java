package al.ib.lawyer.connection;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    public static final String BASE_URL = "http://mylawyerws.fngroups.com/";
    public static final String BASE_IMG_URL = "https://www.egpay.com/storage/";

    private static Retrofit retrofit = null;

    public static Context mcontext;

    public ApiClient(Context context) {
        this.mcontext = context.getApplicationContext();
    }

    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                HttpUrl url = request.url().newBuilder().build();
                                request = request.newBuilder().url(url).build();
                                request.headers("Content-Type application/json; charset=utf-8");
                                request.headers("Cache-Control no-cache");
                                request.header("application/raw");
                                request.headers("application/raw");
                                request.header("Content-Type: raw");
                                return chain.proceed(request);
                            }
                        }).build();

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }


}
