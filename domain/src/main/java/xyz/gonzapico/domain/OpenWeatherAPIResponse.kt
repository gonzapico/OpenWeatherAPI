package xyz.gonzapico.domain

/**
 * Created by gfernandez on 6/27/17.
 */
class OpenWeatherAPIResponse(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: MainWeather,
    val wind: Wind,
    val clouds: Clouds,
    val name: String,
    val cod: Int
)

class Coordinates(
    val long: Double,
    val lat: Double
)

class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

class MainWeather(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)

class Wind(
    val speed: Double,
    val deg: Int
)

class Clouds(
    val all: Int
)