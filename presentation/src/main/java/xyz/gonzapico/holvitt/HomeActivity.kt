package xyz.gonzapico.holvitt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

  val navigator by lazy { app.appComponent.navigator() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)


    navigator.replaceFragment(this, R.id.frameLayout, WeatherFragment.newInstance())
  }

  fun getComponent(){
    app.appComponent
  }

}
