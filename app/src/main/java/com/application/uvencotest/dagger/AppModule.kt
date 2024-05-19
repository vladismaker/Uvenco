package com.application.uvencotest.dagger

import com.application.uvencotest.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideClock(): Clock {
        return SystemClock()
    }

    @Provides
    fun provideTemperatureProvider(): TemperatureProvider {
        return RandomTemperatureProvider()
    }

    @Provides
    @Singleton
    fun provideMainViewModel(clock: Clock, temperatureProvider: TemperatureProvider): MainViewModel {
        return MainViewModel(clock, temperatureProvider)
    }
}