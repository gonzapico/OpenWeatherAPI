package xyz.gonzapico.holvitt

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather.*
import xyz.gonzapico.data.cloud.CITY
import xyz.gonzapico.data.cloud.HELSINKI
import xyz.gonzapico.holvitt.di.components.DaggerFragmentComponent
import xyz.gonzapico.holvitt.di.components.FragmentComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeatherFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment(), WeatherView {

  val fragmentComponent: FragmentComponent by lazy {
    DaggerFragmentComponent
        .builder().applicationComponent(activity.app.appComponent).build()
  }

  var cityToSearch: String = HELSINKI
  lateinit var globalView : View

  @Inject
  lateinit var mWeatherPresenter: WeatherPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val cityArg = arguments.getString(CITY)
    if (!TextUtils.isEmpty(cityArg))
      cityToSearch = cityArg
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    fragmentComponent.inject(this)
    globalView = inflater!!.inflate(R.layout.fragment_weather, container, false)
    return globalView
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mWeatherPresenter.onAttachView(this)
  }

  override fun onResume() {
    super.onResume()
    mWeatherPresenter.getWeatherFrom(cityToSearch)
  }

  companion object {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WeatherFragment.
     */
    fun newInstance(city: String): WeatherFragment {
      val fragment = WeatherFragment()
      val bundle: Bundle = Bundle()
      bundle.putString(CITY, city)
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun showCity(cityName: String) {
    tvCityName.setText(cityName)
  }

  override fun showTemperature(degrees: String) {
    tvTemperature.setText(degrees)
  }

  override fun showLoading() {
    llLoading.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    llLoading.visibility = View.GONE
  }

  override fun showError(message: String) {
    Snackbar.make(globalView, resources.getString(R.string.city_not_found),
        Snackbar.LENGTH_SHORT).show()
  }

  override fun onDestroy() {
    super.onDestroy()
    mWeatherPresenter.onDetach()
  }
}// Required empty public constructor
