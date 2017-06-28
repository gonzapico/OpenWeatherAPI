package xyz.gonzapico.data.di;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gonzapico.data.cloud.WeatherService;

/**
 * Created by gfernandez on 25/02/17.
 */

@Module public class CloudModule {

  String mBaseUrl;
  Context context;

  public CloudModule(String baseUrl, Context context) {
    this.mBaseUrl = baseUrl;
    this.context = context;
  }

  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    return gsonBuilder.create();
  }

  @Provides @Singleton Retrofit provideRetrofit(Gson gson, OkHttpClient.Builder okHttpBuilder) {

    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mBaseUrl)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    retrofit.create(WeatherService.class);

    return retrofit;
  }
}
