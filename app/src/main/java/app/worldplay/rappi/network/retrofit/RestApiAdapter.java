package app.worldplay.rappi.network.retrofit;

import android.content.Context;
import java.util.concurrent.TimeUnit;

import app.worldplay.rappi.BuildConfig;
import app.worldplay.rappi.common.Constants;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestApiAdapter {

    private int cacheSize;
    private Context context;

    public RestApiAdapter(Context context) {
        cacheSize = (5 * 1024 * 1024);
        this.context = context;
    }

    public RestClient EstablecerConexion(int api){
        OkHttpClient.Builder okHttpbuilder = new OkHttpClient().newBuilder().cache(new Cache(context.getCacheDir(), cacheSize));
        okHttpbuilder.addInterceptor(new AppInterceptor());
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpbuilder.addInterceptor(loggingInterceptor);
        }
        okHttpbuilder.retryOnConnectionFailure(false);
        okHttpbuilder.readTimeout(48, TimeUnit.SECONDS);
        okHttpbuilder.writeTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = okHttpbuilder.build();

        if(api == 3){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.apiV3)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(RestClient.class);
        }else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.apiV4)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(RestClient.class);
        }
    }
}