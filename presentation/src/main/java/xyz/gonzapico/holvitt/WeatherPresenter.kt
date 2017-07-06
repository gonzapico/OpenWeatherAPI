package xyz.gonzapico.holvitt

import io.reactivex.observers.DisposableObserver
import xyz.gonzapico.domain.OpenWeatherAPIResponse
import xyz.gonzapico.domain.interactor.GetWeather
import xyz.gonzapico.holvitt.di.PerFragment
import javax.inject.Inject

/**
 * Created by gfernandez on 7/5/17.
 */
@PerFragment class WeatherPresenter @Inject constructor(
    private val mGetWeatherUseCase: GetWeather) {

  lateinit var mWeatherView: WeatherView

  fun onAttachView(weatherView: WeatherView) {
    this.mWeatherView = weatherView
  }

  private fun requestWeatherOf(cityName: String) {
    mGetWeatherUseCase.execute(object : DisposableObserver<OpenWeatherAPIResponse>() {
      override fun onNext(openWeatherAPIResponse: OpenWeatherAPIResponse) {
        mWeatherView.showCity(cityName)
        mWeatherView.showTemperature(openWeatherAPIResponse.main.temp.toString() + "")
      }

      override fun onError(e: Throwable) {

      }

      override fun onComplete() {

      }
    }, GetWeather.WeatherParams.forCity(cityName))
  }

  fun onDetach() {
    mWeatherView.to(null)
  }

  fun getWeatherFrom(city: String) {
    mWeatherView.showLoading()
    if (CITIES.contains(city)) {
      requestWeatherOf(city)
    } else {
      // TODO: We should show an error in the city and in the temperature
      mWeatherView.showError("")
    }
    mWeatherView.hideLoading()
  }

}
