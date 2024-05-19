package com.application.uvencotest.dagger

interface Clock {
    fun getCurrentTime(): String
}

interface TemperatureProvider {
    fun getRandomTemperature(): String
}