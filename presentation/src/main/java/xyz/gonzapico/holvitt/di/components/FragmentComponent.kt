package xyz.gonzapico.holvitt.di.components

import dagger.Component
import xyz.gonzapico.holvitt.WeatherFragment
import xyz.gonzapico.holvitt.di.PerFragment
import xyz.gonzapico.holvitt.di.modules.FragmentModule

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
  fun inject(weatherFragment: WeatherFragment)
}
