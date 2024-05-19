package com.application.uvencotest.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.uvencotest.viewmodels.MainViewModel
import javax.inject.Inject

class MainViewModelFactory  @Inject constructor(
    private val clock: Clock,
    private val temperatureProvider: TemperatureProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(clock, temperatureProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}