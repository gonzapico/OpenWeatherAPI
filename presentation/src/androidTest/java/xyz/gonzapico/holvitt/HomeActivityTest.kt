package xyz.gonzapico.holvitt

import org.junit.Test
import xyz.gonzapico.holvitt.framework.AcceptanceTest

/**
 * Created by gfernandez on 7/5/17.
 */
class HomeActivityTest : AcceptanceTest<HomeActivity>(HomeActivity::class.java) {

  @Test
  fun cityNameShouldBeHelsinki() {
    checkNotNull(R.id.tvCityName)
    checkThat.viewContainsText(R.id.tvCityName, R.string.helsinki)
  }
}