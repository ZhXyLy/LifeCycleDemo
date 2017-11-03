package ai.hou.lifecycledemo.dagger;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import ai.hou.lifecycledemo.webservice.WebService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Module
public class ApiModule {

    private Context appContext;

    public ApiModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return appContext;
    }

    @Provides
    OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(50, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    WebService provideWebService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.15.111:8080/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(WebService.class);
    }
}
