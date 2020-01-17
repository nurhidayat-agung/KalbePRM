package callplan.prm.kalbe.kalbecallplanmobile.app;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import callplan.prm.kalbe.kalbecallplanmobile.BuildConfig;
import callplan.prm.kalbe.kalbecallplanmobile.addons.util.SharedPref;
import callplan.prm.kalbe.kalbecallplanmobile.model.clsHardCode;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkContext {

    public static <T>T createService(Class<T> serviceClass, Context context)
    {
        SharedPref sharedPref = new SharedPref(context);

        Gson gson = new GsonBuilder().setDateFormat(clsHardCode.dateFormat)
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(clsHardCode.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(clsHardCode.conTimeOut, TimeUnit.SECONDS)
                .connectTimeout(clsHardCode.conTimeOut, TimeUnit.SECONDS)
                .writeTimeout(clsHardCode.conTimeOut, TimeUnit.SECONDS)
                .cache(null);

        if (BuildConfig.DEBUG)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }

        // auth
        Interceptor authInterceptor = chain -> {
            Request request = chain.request();
            Headers headers = request.headers().newBuilder()
                    .add("Authorization", "Bearer " + clsHardCode.tokenBearer)
                    .build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        };

        httpClient.addInterceptor(authInterceptor);

        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
