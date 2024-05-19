package com.application.uvencotest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.application.uvencotest.data.DataProvider
import com.application.uvencotest.room.DatabaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun onTerminate() {
        super.onTerminate()

        ioScope.cancel()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        var ioScope = CoroutineScope(Dispatchers.IO)
    }
}