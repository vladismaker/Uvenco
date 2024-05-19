package com.application.uvencotest.dagger

import com.application.uvencotest.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface ApplicationComponent {
    // Инжект зависимостей на экран
    fun inject(activity: MainActivity)

    // Метод для получения фабрики MainViewModel
    fun mainViewModelFactory(): MainViewModelFactory
}