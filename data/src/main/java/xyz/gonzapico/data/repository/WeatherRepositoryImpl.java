package xyz.gonzapico.data.repository;

import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import xyz.gonzapico.data.cloud.WeatherService;
import xyz.gonzapico.data.repository.adapter.RestFactory;
import xyz.gonzapico.domain.OpenWeatherAPIResponse;
import xyz.gonzapico.domain.repository.WeatherRepository;

import static xyz.gonzapico.data.cloud.WeatherServiceKt.APPID;

/**
 * Created by gfernandez on 6/28/17.
 */
@Singleton public class WeatherRepositoryImpl implements WeatherRepository {

  private final RestFactory restAPI;
  private final String urlAPI;

  @Inject WeatherRepositoryImpl(RestFactory api, @Named("ApiUrl") String apiUrl) {
    this.restAPI = api;
    this.urlAPI = apiUrl;
  }

  @NotNull @Override
  public Observable<OpenWeatherAPIResponse> getWeatherInfoOf(@NotNull String city) {
    this.restAPI.setBaseUrl(urlAPI); // Ability to change the API Url at any time.
    return this.restAPI.create(WeatherService.class).getWeatherInfoOf(city, APPID).toObservable();
  }
}
