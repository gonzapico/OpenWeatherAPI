package xyz.gonzapico.holvitt

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather.*
import xyz.gonzapico.data.cloud.HELSINKI
import xyz.gonzapico.holvitt.di.components.ApplicationComponent
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

  @Inject
  lateinit var mWeatherPresenter: WeatherPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    fragmentComponent.inject(this)
    return inflater!!.inflate(R.layout.fragment_weather, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mWeatherPresenter.onAttachView(this)
  }

  override fun onResume() {
    super.onResume()
    mWeatherPresenter.getWeatherFrom(HELSINKI)
  }

  companion object {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WeatherFragment.
     */
    fun newInstance(): WeatherFragment {
      val fragment = WeatherFragment()
      return fragment
    }
  }

  override fun showCity(cityName: String) {
    tvCityName.setText(cityName)
  }

  override fun showTemperature(degrees: String) {
    tvTemperature.setText(degrees)
  }
}// Required empty public constructor
