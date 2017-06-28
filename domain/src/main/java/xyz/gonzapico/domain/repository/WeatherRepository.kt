package xyz.gonzapico.domain.repository

import io.reactivex.Observable
import xyz.gonzapico.domain.OpenWeatherAPIResponse

/**
 * Created by gfernandez on 6/28/17.
 */
interface WeatherRepository {
  fun getWeatherInfoOf(city: String = "Helsinki"): Observable<OpenWeatherAPIResponse>
}