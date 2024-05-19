package com.application.uvencotest.dagger

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

class SystemClock : Clock {
    override fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(currentTime)
    }
}

class RandomTemperatureProvider : TemperatureProvider {
    override fun getRandomTemperature(): String {
        val randomNumber = Random.nextInt(850, 950)
        return (randomNumber.toDouble() / 10.0).toString()
    }
}