package xyz.gonzapico.holvitt.di.modules

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import xyz.gonzapico.holvitt.di.PerFragment

@Module
class FragmentModule(private val fragment: Fragment) {

  @Provides
  @PerFragment
  internal fun fragment(): Fragment {
    return this.fragment
  }
}
