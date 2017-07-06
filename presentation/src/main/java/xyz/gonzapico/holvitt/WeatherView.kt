package xyz.gonzapico.holvitt

/**
 * Created by gfernandez on 7/5/17.
 */
interface WeatherView {

  fun showTemperature(degrees : String)

  fun showCity(cityName : String)

  fun showLoading()

  fun hideLoading()

  fun showError(message : String)
}