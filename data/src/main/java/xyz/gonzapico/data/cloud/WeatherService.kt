package xyz.gonzapico.data.cloud

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.gonzapico.domain.OpenWeatherAPIResponse

/**
 * Created by gfernandez on 6/28/17.
 */
const val WEATHER_PATH = "weather/"
const val APPID = "11aea0ba906f81c3c865ac0f6d108428"
const val HELSINKI = "Helsinki"
const val CITY = "city"

interface WeatherService {
  // http://api.openweathermap.org/data/2.5/weather?q=Helsinki&APPID=11aea0ba906f81c3c865ac0f6d108428
  @GET(WEATHER_PATH)
  fun getWeatherInfoOf(@Query("q") city: String = HELSINKI,
      @Query("APPID") appId: String = APPID): Observable<OpenWeatherAPIResponse>
}