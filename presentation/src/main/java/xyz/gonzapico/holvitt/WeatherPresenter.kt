package xyz.gonzapico.holvitt

import io.reactivex.observers.DisposableObserver
import javax.inject.Inject
import xyz.gonzapico.domain.OpenWeatherAPIResponse
import xyz.gonzapico.domain.interactor.GetWeather
import xyz.gonzapico.holvitt.di.PerFragment

/**
 * Created by gfernandez on 7/5/17.
 */
@PerFragment class WeatherPresenter @Inject constructor(
    private val mGetWeatherUseCase: GetWeather) {
  private var mWeatherView: WeatherView? = null

  fun onAttachView(weatherView: WeatherView) {
    this.mWeatherView = weatherView
  }

  fun getWeatherFrom(cityName: String) {
    mGetWeatherUseCase.execute(object : DisposableObserver<OpenWeatherAPIResponse>() {
      override fun onNext(openWeatherAPIResponse: OpenWeatherAPIResponse) {
        mWeatherView!!.showCity(cityName)
        mWeatherView!!.showTemperature(openWeatherAPIResponse.main.temp.toString() + "")
      }

      override fun onError(e: Throwable) {

      }

      override fun onComplete() {

      }
    }, GetWeather.WeatherParams.forCity(cityName))
  }

  private fun onDetach() {
    mWeatherView = null
  }
}
