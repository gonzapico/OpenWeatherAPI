package xyz.gonzapico.holvitt.di.components

import android.content.Context
import dagger.Component
import javax.inject.Singleton
import xyz.gonzapico.domain.executor.PostExecutionThread
import xyz.gonzapico.domain.executor.ThreadExecutor
import xyz.gonzapico.domain.repository.WeatherRepository
import xyz.gonzapico.holvitt.HomeActivity
import xyz.gonzapico.holvitt.di.modules.ApplicationModule
import xyz.gonzapico.holvitt.navigator.Navigator

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun inject(activity: HomeActivity)
  fun context(): Context
  fun navigator(): Navigator
  fun threadExecutor(): ThreadExecutor

  fun postExecutionThread(): PostExecutionThread
  fun weatherRepository(): WeatherRepository
}
