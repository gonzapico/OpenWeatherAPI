package xyz.gonzapico.domain.interactor

import io.reactivex.Observable
import javax.inject.Inject
import xyz.gonzapico.domain.repository.WeatherRepository
import xyz.gonzapico.domain.executor.PostExecutionThread
import xyz.gonzapico.domain.executor.ThreadExecutor
import xyz.gonzapico.domain.OpenWeatherAPIResponse

/**
 * Created by gfernandez on 6/28/17.
 */

class GetWeather @Inject constructor(private val mRepository: WeatherRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) : BaseUseCase<OpenWeatherAPIResponse, GetWeather.WeatherParams>(
    threadExecutor, postExecutionThread) {

  internal override fun buildUseCaseObservable(
      params: WeatherParams): Observable<OpenWeatherAPIResponse> {
    return this.mRepository.getWeatherInfoOf(params.mCity)
  }

  class WeatherParams private constructor(val mCity: String) {
    companion object {

      fun forCity(city: String): GetWeather.WeatherParams {
        return GetWeather.WeatherParams(city)
      }
    }
  }
}


