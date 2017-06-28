package xyz.gonzapico.domain.interactor;

import io.reactivex.Observable;
import javax.inject.Inject;
import xyz.gonzapico.domain.repository.WeatherRepository;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;
import xyz.gonzapico.domain.OpenWeatherAPIResponse;

/**
 * Created by gfernandez on 6/28/17.
 */

public class GetWeather extends BaseUseCase<OpenWeatherAPIResponse, GetWeather.WeatherParams> {
  private final WeatherRepository mRepository;

  @Inject public GetWeather(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = weatherRepository;
  }

  @Override Observable<OpenWeatherAPIResponse> buildUseCaseObservable(WeatherParams params) {
    return this.mRepository.getWeatherInfoOf(params.mCity);
  }

  public static final class WeatherParams {

    private final String mCity;

    private WeatherParams(String city) {
      this.mCity = city;
    }

    public static GetWeather.WeatherParams forCity(String city) {
      return new GetWeather.WeatherParams(city);
    }
  }
}


