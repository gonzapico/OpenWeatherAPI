package xyz.gonzapico.holvitt

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_home.*
import xyz.gonzapico.data.cloud.HELSINKI


class HomeActivity : AppCompatActivity() {

  val navigator by lazy { app.appComponent.navigator() }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    setSupportActionBar(toolbar)
    CITIES = resources.getStringArray(R.array.cities_to_search)

    navigator.replaceFragment(this, R.id.frameLayout, WeatherFragment.newInstance(HELSINKI))
  }


  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    handleSearchableValue(intent!!)
  }

  private fun handleSearchableValue(intent: Intent) {

    if (Intent.ACTION_SEARCH == intent.action) {
      val cityQuery = intent.getStringExtra(SearchManager.QUERY)
      Log.d("cityQuery", "cityQuery --> " + cityQuery)
      navigator.replaceFragment(this, R.id.frameLayout, WeatherFragment.newInstance(cityQuery))
    }
  }


  fun getComponent() {
    app.appComponent
  }

  override
  fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.search_menu, menu)
    val searchItem = menu.findItem(R.id.search)
    val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
    // Associate searchable configuration with the SearchView
    val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
    searchView.setSearchableInfo(
        searchManager.getSearchableInfo(componentName))


    return super.onCreateOptionsMenu(menu)
  }
}
